package com.example.nestedadapterexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nestedadapterexample.R;
import com.example.nestedadapterexample.model.Actor;
import com.playmoweb.nestedadapter.SectionAdapter;

/**
 * Created by thibaud on 22/06/2017.
 */
public class ActorAdapter extends SectionAdapter<Actor> {

    /**
     * The top header of the actors list
     */
    public static class ActorContainer extends SectionAdapter<Void> {
        @Override
        protected Integer getHeaderResourceLayout() {
            return R.layout.actor_header_list_item;
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
        return new ActorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ActorViewHolder view = (ActorViewHolder) viewHolder;
        Actor actor = items.get(position);
        view.actorName.setText(actor.getName());
    }

    @Override
    protected Integer getResourceLayout() {
        return R.layout.actor_list_item;
    }
}
