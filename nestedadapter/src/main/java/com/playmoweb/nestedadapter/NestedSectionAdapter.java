package com.playmoweb.nestedadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * NestedSectionAdapter
 *
 * @author Thibaud Giovannetti
 * @by Playmoweb
 * @created 21/07/2017
 */
public class NestedSectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    public enum ViewType {
        HEADER, CONTENT, FOOTER
    }

    /**
     * Immutable class to store datas in flatten()
     */
    private static class ItemAtPosition {
        final SectionAdapter adapter;
        final ViewType type;

        ItemAtPosition(final SectionAdapter adapter, final ViewType type) {
            this.adapter = adapter;
            this.type = type;
        }
    }

    protected Node graph;
    private ArrayList<ItemAtPosition> cachedOrderItems;

    public NestedSectionAdapter() {
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                cachedOrderItems = flatten(graph); // Observe data update and refresh cached datas
            }
        });
    }

    @NonNull
    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ItemAtPosition found = getAdapterForType(viewType);
        if (found == null) {
            throw new UnsupportedOperationException("There is no adapter to handle this type");
        }

        switch (found.type) {
            case HEADER:
                return found.adapter.onCreateHeaderViewHolder(parent, viewType);
            case CONTENT:
                return found.adapter.onCreateViewHolder(parent, viewType);
            case FOOTER:
                return found.adapter.onCreateFooterViewHolder(parent, viewType);
        }

        throw new IndexOutOfBoundsException(viewType + " does not exist !");
    }

    @Override
    public void onBindViewHolder(@NonNull final SectionAdapter.ViewHolder holder, final int position) {
        final ItemAtPosition found = getCachedOrderItems().get(position);
        final ViewType type = ViewType.values()[found.type.ordinal()];

        int adapterStartPosition = 0;
        for (int i = position - 1; i >= 0; i--) { // find the last common adapter position to define the index in it
            if (getCachedOrderItems().get(i).adapter != found.adapter) {
                break;
            }
            adapterStartPosition++;
        }

        switch (type) {
            case HEADER:
                found.adapter.onBindHeaderViewHolder((SectionAdapter.HeaderViewHolder) holder, adapterStartPosition);
                break;
            case CONTENT:
                found.adapter.onBindViewHolder(holder, adapterStartPosition);
                break;
            case FOOTER:
                found.adapter.onBindFooterViewHolder((SectionAdapter.FooterViewHolder) holder, adapterStartPosition);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return getCachedOrderItems().size();
    }

    @Override
    public int getItemViewType(final int position) {
        final ItemAtPosition found = cachedOrderItems.get(position);
        if (found == null) {
            throw new IndexOutOfBoundsException("Looks like there is no adapter for this index : " + position);
        }
        return found.adapter.getResourceTypeFor(found.type);
    }

    /**
     * Remove an item
     */
    protected void removeItem(final SectionAdapter adapter, final int position) {
        int globalPosition = -1;
        for (final ItemAtPosition cachedOrderItem : cachedOrderItems) {
            if (cachedOrderItem.adapter == adapter) {
                globalPosition += position;
                cachedOrderItem.adapter.getItems().remove(position);
                notifyItemRemoved(globalPosition);
                notifyItemRangeChanged(globalPosition, cachedOrderItems.size());
                cachedOrderItems = flatten(graph); // re-flatten full graph
                return;
            } else {
                globalPosition += cachedOrderItem.adapter.getItems() != null ? cachedOrderItem.adapter.getItems().size() : 0;
                globalPosition += cachedOrderItem.adapter.hasHeader() ? 1 : 0;
                globalPosition += cachedOrderItem.adapter.hasFooter() ? 1 : 0;
            }
        }

    }

    private ItemAtPosition getAdapterForType(final int layoutType) {
        for (final ItemAtPosition item : getCachedOrderItems()) {
            if (item.adapter.getResourceTypeFor(item.type) == layoutType) {
                return item;
            }
        }
        return null;
    }

    private ArrayList<ItemAtPosition> getCachedOrderItems() {
        if (cachedOrderItems == null) {
            cachedOrderItems = flatten(graph);
        }
        return cachedOrderItems;
    }

    /**
     * Transform a graph of Nodes into a flatten structure (ArrayList) to reduce access complexity to O(1)
     *
     * @note This method is recursive and can use some memory when called.
     * @todo Rewrite/reverse this method to prevent memory usage on complexe graph.
     */
    private ArrayList<ItemAtPosition> flatten(final Node currentNode) {
        if (currentNode == null) {
            return new ArrayList<>(0);
        }

        final ArrayList<ItemAtPosition> datas = new ArrayList<>();
        if (currentNode.getAdapter() != null) {
            if (currentNode.getAdapter().hasHeader()) {
                datas.add(new ItemAtPosition(currentNode.getAdapter(), ViewType.HEADER));
            }

            if (currentNode.getAdapter().hasContent()) {
                for (int i = 0, count = currentNode.getAdapter().items.size(); i < count; i++) {
                    datas.add(new ItemAtPosition(currentNode.getAdapter(), ViewType.CONTENT));
                }
            }
        }

        if (currentNode.child != null) {
            for (final Node n : currentNode.getChilds()) {
                datas.addAll(flatten(n));
            }
        }

        if (currentNode.getAdapter() != null && currentNode.getAdapter().hasFooter()) {
            datas.add(new ItemAtPosition(currentNode.getAdapter(), ViewType.FOOTER));
        }

        return datas;
    }
}
