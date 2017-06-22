package com.example.nestedadapterexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nestedadapterexample.R;
import com.example.nestedadapterexample.model.Actor;
import com.playmoweb.nestedadapter.NestedSectionAdapter;
import com.playmoweb.nestedadapter.SectionAdapter;

/**
 * Created by thibaud on 22/06/2017.
 */

public class ActorAdapter extends SectionAdapter<Actor> {

    public static class ActorContainer extends SectionAdapter<Void> {
        @Override
        public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.actor_header_list_item, parent, false);
            return new HeaderViewHolder(v);
        }

        @Override
        public int getResourceTypeFor(NestedSectionAdapter.ViewType type) {
            if(type == NestedSectionAdapter.ViewType.HEADER)
                return R.layout.actor_header_list_item;
            return 0;
        }

        @Override
        public boolean hasHeader() {
            return true;
        }
    }

    private class ActorViewHolder extends ViewHolder {
        TextView actorName;

        public ActorViewHolder(View itemView) {
            super(itemView);
            actorName = (TextView) itemView.findViewById(R.id.actorName);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.actor_list_item, parent, false);
        return new ActorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ActorViewHolder view = (ActorViewHolder) viewHolder;
        Actor actor = items.get(position);

        view.actorName.setText(actor.getName());
    }

    @Override
    public boolean hasContent() {
        return true;
    }

    @Override
    public int getResourceTypeFor(NestedSectionAdapter.ViewType type) {
        if(type == NestedSectionAdapter.ViewType.CONTENT){
            return R.layout.actor_list_item;
        }
        return 0;
    }
}
