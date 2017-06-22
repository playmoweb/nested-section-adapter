package com.example.nestedadapterexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nestedadapterexample.R;
import com.playmoweb.nestedadapter.NestedSectionAdapter;
import com.playmoweb.nestedadapter.SectionAdapter;

/**
 * Wrap the list in a header and a footer
 * Created by thibaud on 22/06/2017.
 */
public class WrapperAdapter extends SectionAdapter<Void> {

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.wrapper_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.wrapper_footer, parent, false);
        return new FooterViewHolder(v);
    }

    @Override
    public boolean hasHeader() {
        return true;
    }

    @Override
    public boolean hasFooter() {
        return true;
    }

    @Override
    public int getResourceTypeFor(NestedSectionAdapter.ViewType type) {
        if(type == NestedSectionAdapter.ViewType.HEADER) {
            return R.layout.wrapper_header;
        } else if(type == NestedSectionAdapter.ViewType.FOOTER) {
            return R.layout.wrapper_footer;
        }
        return 0;
    }
}
