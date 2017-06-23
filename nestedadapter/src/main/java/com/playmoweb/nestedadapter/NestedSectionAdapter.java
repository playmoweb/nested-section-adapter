package com.playmoweb.nestedadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * NestedSectionAdapter
 * @author      Thibaud Giovannetti
 * @by          Playmoweb
 * @created     21/07/2017
 */
public class NestedSectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    public enum ViewType {
        HEADER, CONTENT, FOOTER
    }

    /**
     * Immutable class to store datas in flatenize()
     */
    private class ItemAtPosition {
        final SectionAdapter adapter;
        final ViewType type;

        ItemAtPosition(SectionAdapter adapter, ViewType type) {
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
                cachedOrderItems = flatenize(graph); // Observe data update and refresh cached datas
            }
        });
    }

    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAtPosition found = getAdapterForType(viewType);
        if(found == null){
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

    private ItemAtPosition getAdapterForType(int layoutType){
        for(ItemAtPosition item : getCachedOrderItems()){
            if(item.adapter.getResourceTypeFor(item.type) == layoutType){
                return item;
            }
        }
        return null;
    }

    private ArrayList<ItemAtPosition> getCachedOrderItems(){
        if(cachedOrderItems == null){
            cachedOrderItems = flatenize(graph);
        }
        return cachedOrderItems;
    }

    @Override
    public void onBindViewHolder(SectionAdapter.ViewHolder holder, int position) {
        ItemAtPosition found = getCachedOrderItems().get(position);
        ViewType type = ViewType.values()[found.type.ordinal()];

        int adapterStartPosition = 0;
        for(int i = position - 1; i >= 0; i--){ // find the last common adapter position to define the index in it
            if(getCachedOrderItems().get(i).adapter != found.adapter){
                break;
            }
            adapterStartPosition++;
        }

        switch (type){
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
    public int getItemViewType(int position) {
        ItemAtPosition found = cachedOrderItems.get(position);
        if(found == null){
            throw new IndexOutOfBoundsException("Looks like there is no adapter for this index : " + position);
        }
        return found.adapter.getResourceTypeFor(found.type);
    }

    /**
     * Transform a graph of Nodes into a flatten structure (ArrayList) to reduce access complexity to O(1)
     * @note    This method is recursive and can use some memory when called.
     * @todo    Rewrite/reverse this method to prevent memory usage on complexe graph.
     */
    private ArrayList<ItemAtPosition> flatenize(Node currentNode){
        if(currentNode == null) {
            return new ArrayList<>(0);
        }

        ArrayList<ItemAtPosition> datas = new ArrayList<>();
        if(currentNode.getAdapter() != null){
            if(currentNode.getAdapter().hasHeader()) {
                datas.add(new ItemAtPosition(currentNode.getAdapter(), ViewType.HEADER));
            }

            if(currentNode.getAdapter().hasContent()){
                for(int i = 0, count = currentNode.getAdapter().items.size(); i < count; i++) {
                    datas.add(new ItemAtPosition(currentNode.getAdapter(), ViewType.CONTENT));
                }
            }
        }

        if(currentNode.child != null){
            for(Node n : currentNode.getChilds()){
                datas.addAll(flatenize(n));
            }
        }

        if(currentNode.getAdapter() != null && currentNode.getAdapter().hasFooter()) {
            datas.add(new ItemAtPosition(currentNode.getAdapter(), ViewType.FOOTER));
        }

        return datas;
    }
}
