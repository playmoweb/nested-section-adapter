package com.example.nestedadapterexample;

import com.example.nestedadapterexample.adapter.ActorAdapter;
import com.example.nestedadapterexample.adapter.MovieAdapter;
import com.example.nestedadapterexample.adapter.RewardAdapter;
import com.example.nestedadapterexample.adapter.WrapperAdapter;
import com.example.nestedadapterexample.model.Movie;
import com.playmoweb.nestedadapter.NestedSectionAdapter;
import com.playmoweb.nestedadapter.Node;

import java.util.List;

/**
 * Created by thibaud on 22/06/2017.
 */
public class MyListAdapter extends NestedSectionAdapter {

    /**
     * Set the movies and update the recycler view
     * @param movies
     */
    public void setMovies(List<Movie> movies) {
        WrapperAdapter wrapperAdapter = new WrapperAdapter();
        graph = new Node(wrapperAdapter);

        for(Movie movie : movies){
            MovieAdapter movieAdapter = new MovieAdapter();
            movieAdapter.setMovie(movie);

            // rewards
            RewardAdapter rewardsAdapter = new RewardAdapter();
            rewardsAdapter.setItems(movie.getRewards());

            RewardAdapter.RewardContainer rewardContainer = new RewardAdapter.RewardContainer();
            Node reward = new Node(rewardContainer);
            reward.addChild(new Node(rewardsAdapter));

            // actors
            ActorAdapter actorsAdapter = new ActorAdapter();
            actorsAdapter.setItems(movie.getActors());

            ActorAdapter.ActorContainer actorContainer = new ActorAdapter.ActorContainer();
            Node actor = new Node(actorContainer);
            actor.addChild(new Node(actorsAdapter));

            Node movieNode = new Node(movieAdapter);
            movieNode.addChild(actor);
            movieNode.addChild(reward);

            graph.addChild(movieNode);
        }

        notifyDataSetChanged();
    }
}
