package com.example.nestedadapterexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nestedadapterexample.R;
import com.example.nestedadapterexample.model.Movie;
import com.playmoweb.nestedadapter.SectionAdapter;

/**
 * Movie adapter
 * Created by thibaud on 22/06/2017.
 */
public class MovieAdapter extends SectionAdapter<Movie> {
    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    private class MovieHeaderViewHolder extends HeaderViewHolder {
        TextView movieTitle;
        TextView movieType;

        MovieHeaderViewHolder(View itemView) {
            super(itemView);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieType = (TextView) itemView.findViewById(R.id.movieType);
        }
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new MovieHeaderViewHolder(v);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int position) {
        MovieHeaderViewHolder header = (MovieHeaderViewHolder) viewHolder;

        header.movieTitle.setText(movie.getName());
        header.movieType.setText(movie.getType());
    }

    @Override
    protected Integer getHeaderResourceLayout() {
        return R.layout.movie_list_item;
    }
}
