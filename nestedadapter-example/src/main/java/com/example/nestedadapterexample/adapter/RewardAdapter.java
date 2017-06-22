package com.example.nestedadapterexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nestedadapterexample.R;
import com.example.nestedadapterexample.model.Reward;
import com.playmoweb.nestedadapter.NestedSectionAdapter;
import com.playmoweb.nestedadapter.SectionAdapter;

/**
 * Created by thibaud on 22/06/2017.
 */

public class RewardAdapter extends SectionAdapter<Reward> {

    public static class RewardContainer extends SectionAdapter<Void> {
        @Override
        public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.reward_header_list_item, parent, false);
            return new HeaderViewHolder(v);
        }

        @Override
        public int getResourceTypeFor(NestedSectionAdapter.ViewType type) {
            if(type == NestedSectionAdapter.ViewType.HEADER)
                return R.layout.reward_header_list_item;
            return 0;
        }

        @Override
        public boolean hasHeader() {
            return true;
        }
    }

    private class RewardViewHolder extends ViewHolder {
        TextView rewardName;
        TextView rewardDate;

        public RewardViewHolder(View itemView) {
            super(itemView);
            rewardName = (TextView) itemView.findViewById(R.id.rewardName);
            rewardDate = (TextView) itemView.findViewById(R.id.rewardDate);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.reward_list_item, parent, false);
        return new RewardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        RewardViewHolder view = (RewardViewHolder) viewHolder;
        Reward reward = items.get(position);

        view.rewardName.setText(reward.getName());
        view.rewardDate.setText(reward.getObtained().toString());
    }

    @Override
    public boolean hasContent() {
        return true;
    }

    @Override
    public int getResourceTypeFor(NestedSectionAdapter.ViewType type) {
        if(type == NestedSectionAdapter.ViewType.CONTENT){
            return R.layout.reward_list_item;
        }
        return 0;
    }
}
