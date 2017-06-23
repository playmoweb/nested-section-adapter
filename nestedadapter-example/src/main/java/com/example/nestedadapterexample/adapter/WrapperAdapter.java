package com.example.nestedadapterexample.adapter;

import com.example.nestedadapterexample.R;
import com.playmoweb.nestedadapter.SectionAdapter;

/**
 * Wrap the list in a header and a footer
 * Created by thibaud on 22/06/2017.
 */
public class WrapperAdapter extends SectionAdapter<Void> {
    @Override
    protected Integer getHeaderResourceLayout() {
        return R.layout.wrapper_header;
    }

    @Override
    protected Integer getFooterResourceLayout() {
        return R.layout.wrapper_footer;
    }
}
