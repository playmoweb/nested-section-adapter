package com.example.nestedadapterexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nestedadapterexample.model.Actor;
import com.example.nestedadapterexample.model.Movie;
import com.example.nestedadapterexample.model.Reward;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * MainActivity
 * Display a list of movies
 */
public class MainActivity extends AppCompatActivity {
    private static final int MULTIPLIER_TO_DUPLICATE_MOVIES = 1; // if you want to test a very long list, try to put 100 here (~7000 items generated)
    private static final DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

    private RecyclerView myList;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyListAdapter();

        myList = (RecyclerView) findViewById(R.id.myList);
        myList.setAdapter(adapter);
        myList.setLayoutManager(new LinearLayoutManager(this));
        myList.requestFocus();

        ArrayList<Movie> allMovies = new ArrayList<>();
        for(int i = 0; i < MULTIPLIER_TO_DUPLICATE_MOVIES; i++) {
            allMovies.addAll(getMovies());
        }

        adapter.setMovies(allMovies);
    }

    /**
     * Create all movies with associated datas
     */
    private List<Movie> getMovies(){
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Episode IV - A New Hope", "Fantasy", getDate("May 25, 1977"))
                .addActor(new Actor("Mark Hamill"))
                .addActor(new Actor("Harrison Ford"))
                .addActor(new Actor("Carrie Fisher"))
                .addActor(new Actor("Peter Cushing"))
                .addActor(new Actor("Alec Guinness"))
                .addReward(new Reward("Reward 1", new Date()))
                .addReward(new Reward("Reward 2", new Date()))
                .addReward(new Reward("Reward 3", new Date()))
        );
        movies.add(new Movie("Episode V - The Empire Strikes Back", "Fantasy", getDate("May 21, 1980"))
                .addActor(new Actor("Mark Hamill"))
                .addActor(new Actor("Harrison Ford"))
                .addActor(new Actor("Carrie Fisher"))
                .addActor(new Actor("Peter Cushing"))
                .addActor(new Actor("Alec Guinness"))
                .addReward(new Reward("Reward 4", new Date()))
                .addReward(new Reward("Reward 5", new Date()))
                .addReward(new Reward("Reward 6", new Date()))
        );
        movies.add(new Movie("Episode VI- Return of the Jedi", "Fantasy", getDate("May 25, 1983"))
                .addActor(new Actor("Mark Hamill"))
                .addActor(new Actor("Harrison Ford"))
                .addActor(new Actor("Carrie Fisher"))
                .addActor(new Actor("Peter Cushing"))
                .addActor(new Actor("Alec Guinness"))
                .addReward(new Reward("Reward 7", new Date()))
                .addReward(new Reward("Reward 8", new Date()))
                .addReward(new Reward("Reward 9", new Date()))
        );
        movies.add(new Movie("Episode I - The Phantom Menace", "Fantasy", getDate("May 19, 1999"))
                .addActor(new Actor("Mark Hamill"))
                .addActor(new Actor("Harrison Ford"))
                .addActor(new Actor("Carrie Fisher"))
                .addActor(new Actor("Peter Cushing"))
                .addActor(new Actor("Alec Guinness"))
                .addReward(new Reward("Reward 10", new Date()))
                .addReward(new Reward("Reward 11", new Date()))
                .addReward(new Reward("Reward 12", new Date()))
        );
        movies.add(new Movie("Episode II - Attack of the Clones", "Fantasy", getDate("May 16, 2002"))
                .addActor(new Actor("Mark Hamill"))
                .addActor(new Actor("Harrison Ford"))
                .addActor(new Actor("Carrie Fisher"))
                .addActor(new Actor("Peter Cushing"))
                .addActor(new Actor("Alec Guinness"))
                .addReward(new Reward("Reward 13", new Date()))
                .addReward(new Reward("Reward 14", new Date()))
                .addReward(new Reward("Reward 15", new Date()))
        );
        movies.add(new Movie("Episode III - Revenge of the Sith", "Fantasy", getDate("May 19, 2005"))
                .addActor(new Actor("Mark Hamill"))
                .addActor(new Actor("Harrison Ford"))
                .addActor(new Actor("Carrie Fisher"))
                .addActor(new Actor("Peter Cushing"))
                .addActor(new Actor("Alec Guinness"))
                .addReward(new Reward("Reward 16", new Date()))
        );
        movies.add(new Movie("Episode VII", "Fantasy - The Force Awakens", getDate("December 18, 2015"))
                .addActor(new Actor("Mark Hamill"))
                .addActor(new Actor("Harrison Ford"))
                .addActor(new Actor("Carrie Fisher"))
                .addActor(new Actor("Peter Cushing"))
                .addActor(new Actor("Alec Guinness"))
                .addReward(new Reward("Reward 17", new Date()))
                .addReward(new Reward("Reward 18", new Date()))
                .addReward(new Reward("Reward 19", new Date()))
                .addReward(new Reward("Reward 20", new Date()))
                .addReward(new Reward("Reward 21", new Date()))
                .addReward(new Reward("Reward 22", new Date()))
                .addReward(new Reward("Reward 23", new Date()))
                .addReward(new Reward("Reward 24", new Date()))
                .addReward(new Reward("Reward 25", new Date()))
        );

        return movies;
    }

    private Date getDate(String str){
        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
