package com.playmoweb.nestedadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by thibaud on 21/06/2017.
 */

public abstract class SectionAdapter<T> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    protected List<T> items;

    public void setItems(List<T> items){
        this.items = items;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType){
        return null;
    }

    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType){
        return null;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {

    }

    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int position) {

    }

    public void onBindFooterViewHolder(FooterViewHolder viewHolder, int position) {

    }

    public abstract int getResourceTypeFor(NestedSectionAdapter.ViewType type);

    public boolean hasHeader(){
        return false;
    }

    public boolean hasFooter(){
        return false;
    }

    public boolean hasContent(){
        return false;
    }
}
