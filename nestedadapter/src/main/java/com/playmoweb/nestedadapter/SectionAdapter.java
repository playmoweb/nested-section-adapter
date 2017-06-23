package com.playmoweb.nestedadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * SectionAdapter
 * @author      Thibaud Giovannetti
 * @by          Playmoweb
 * @created     21/07/2017
 */
public abstract class SectionAdapter<T> {

    /**
     * Default ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * Default HeaderViewHolder
     */
    public class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * Default FooterViewHolder
     */
    public class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * List of items stored in this SectionAdapter.
     * @note    These items are only used by the ViewHolder.
     *          You must pass object through custom methods to use them in Header and Footer.
     */
    protected List<T> items;

    public void setItems(List<T> items){
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(hasContent()) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getResourceLayout(), parent, false));
        }
        return null;
    }

    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType){
        if(hasHeader()) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(getHeaderResourceLayout(), parent, false));
        }
        return null;
    }

    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType){
        if(hasFooter()) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(getFooterResourceLayout(), parent, false));
        }
        return null;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {

    }

    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int position) {

    }

    public void onBindFooterViewHolder(FooterViewHolder viewHolder, int position) {

    }

    protected Integer getHeaderResourceLayout(){
        return null; // not layout defined
    }

    protected Integer getFooterResourceLayout(){
        return null; // not layout defined
    }

    protected Integer getResourceLayout(){
        return null; // not layout defined
    }

    /**
     * Return the layout for the type if defined
     */
    public int getResourceTypeFor(NestedSectionAdapter.ViewType type) {
        if(type == NestedSectionAdapter.ViewType.CONTENT && hasContent()){
            return getResourceLayout();
        } else if(type == NestedSectionAdapter.ViewType.HEADER && hasHeader()){
            return getHeaderResourceLayout();
        } else if(type == NestedSectionAdapter.ViewType.FOOTER && hasFooter()){
            return getFooterResourceLayout();
        }
        return 0;
    }

    public boolean hasHeader(){
        return getHeaderResourceLayout() != null;
    }

    public boolean hasFooter(){
        return getFooterResourceLayout() != null;
    }

    public boolean hasContent(){
        return getResourceLayout() != null;
    }
}
