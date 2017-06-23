# About

An adapter to allow multiple and nested sections in recycler view.

## Structure

An SectionAdapter can have :
- A header
- A content (list of items or another SectionAdapter)
- A footer

# Example

You can find a full working example in the nestedadapter-example folder.

<div align="center">
  <img src="web/preview-example.gif" alt="The list animated" />
  <br />
  <em>Star wars example</em>
</div>

# Usage

From the example. Define as many SectionAdapter you need :

```java
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
```

```java
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
```


Actor adapter : 

```java
public class ActorAdapter extends SectionAdapter<Actor> {
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
        Actor actor = items.get(position); // get current actor for this position
        view.actorName.setText(actor.getName());
    }

    @Override
    protected Integer getResourceLayout() {
        return R.layout.actor_list_item;
    }
}
```


Create a class which inherit NestedSectionAdapter :

```java
class MovieNestedAdapter extends NestedSectionAdapter {

    public void setMovies(List<Movie> movies) {
        WrapperAdapter wrapperAdapter = new WrapperAdapter();
        graph = new Node(wrapperAdapter); // create a root node

        for(Movie movie : movies){
            MovieAdapter movieAdapter = new MovieAdapter();
            movieAdapter.setMovie(movie);

            // actors
            ActorAdapter actorsAdapter = new ActorAdapter();
            actorsAdapter.setItems(movie.getActors());
            
            // create the Node and add them to the graph
            Node movieNode = new Node(movieAdapter);
            movieNode.addChild(new Node(actorsAdapter));
            graph.addChild(movieNode);
        }

        notifyDataSetChanged();
    }
}

```
