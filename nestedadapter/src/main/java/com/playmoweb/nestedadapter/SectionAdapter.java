package com.playmoweb.nestedadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * SectionAdapter
 *
 * @author Thibaud Giovannetti
 * @by Playmoweb
 * @created 21/07/2017
 */
public abstract class SectionAdapter<T> {

    /**
     * Default ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);
        }
    }

    /**
     * Default HeaderViewHolder
     */
    public static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(final View itemView) {
            super(itemView);
        }
    }

    /**
     * Default FooterViewHolder
     */
    public static class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(final View itemView) {
            super(itemView);
        }
    }

    /**
     * List of items stored in this SectionAdapter.
     *
     * @note These items are only used by the ViewHolder.
     * You must pass object through custom methods to use them in Header and Footer.
     */
    protected List<T> items;

    public void setItems(final List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (hasContent()) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getResourceLayout(), parent, false));
        }
        return null;
    }

    public HeaderViewHolder onCreateHeaderViewHolder(final ViewGroup parent, final int viewType) {
        if (hasHeader()) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(getHeaderResourceLayout(), parent, false));
        }
        return null;
    }

    public FooterViewHolder onCreateFooterViewHolder(final ViewGroup parent, final int viewType) {
        if (hasFooter()) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(getFooterResourceLayout(), parent, false));
        }
        return null;
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

    }

    public void onBindHeaderViewHolder(final HeaderViewHolder viewHolder, final int position) {

    }

    public void onBindFooterViewHolder(final FooterViewHolder viewHolder, final int position) {

    }

    protected Integer getHeaderResourceLayout() {
        return null; // not layout defined
    }

    protected Integer getFooterResourceLayout() {
        return null; // not layout defined
    }

    protected Integer getResourceLayout() {
        return null; // not layout defined
    }

    /**
     * Return the layout for the type if defined
     */
    public int getResourceTypeFor(final NestedSectionAdapter.ViewType type) {
        if (type == NestedSectionAdapter.ViewType.CONTENT && hasContent()) {
            return getResourceLayout();
        } else if (type == NestedSectionAdapter.ViewType.HEADER && hasHeader()) {
            return getHeaderResourceLayout();
        } else if (type == NestedSectionAdapter.ViewType.FOOTER && hasFooter()) {
            return getFooterResourceLayout();
        }
        return 0;
    }

    public boolean hasHeader() {
        return getHeaderResourceLayout() != null;
    }

    public boolean hasFooter() {
        return getFooterResourceLayout() != null;
    }

    public boolean hasContent() {
        return getResourceLayout() != null;
    }
}
