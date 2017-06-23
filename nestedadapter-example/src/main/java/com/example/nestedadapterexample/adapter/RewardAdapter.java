package com.example.nestedadapterexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nestedadapterexample.R;
import com.example.nestedadapterexample.model.Reward;
import com.playmoweb.nestedadapter.SectionAdapter;

/**
 * Created by thibaud on 22/06/2017.
 */
public class RewardAdapter extends SectionAdapter<Reward> {

    /**
     * The top header of the rewards list
     * @note    When the layout does not need customization, you can only override getHeaderResourceLayout()
     */
    public static class RewardContainer extends SectionAdapter<Void> {
        @Override
        protected Integer getHeaderResourceLayout() {
            return R.layout.reward_header_list_item;
        }
    }

    private class RewardViewHolder extends ViewHolder {
        TextView rewardName;
        TextView rewardDate;

        RewardViewHolder(View itemView) {
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
    protected Integer getResourceLayout() {
        return R.layout.reward_list_item;
    }
}
