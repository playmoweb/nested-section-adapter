# About

An adapter to allow multiple and nested sections in recycler view.

## Structure

An SectionAdapter can have :
- A header
- A content (list of items)
- A footer

# Example

In this example we will try to do this nested nested recycler view as below :
- Header of the list
  - Type of movie
      - movie title
          - Actors
             - actor 1
             - actor 2
          - Rewards
             - reward 1
             - reward 2
      - movie title
          - Actors
          - Rewards
             - reward 1
  - Type of movie
      - movie title
          - Actors
             - actor 1
             - actor 2
          - Rewards
             - reward 1
             - reward 2
      - movie title
          - Actors
             - actor 1
          - Rewards
- Footer of the list



Some data classes : 

```java
class Movie {
    private String name;
    private String type;
    private Date year;
    
    private List<Actor> actors;
    private List<Reward> rewards;
    
    // ... getters & setters
}

class Actor {
    private String name;
    private String description;
}

class Reward {
    private String name;
    private Date obtained;
}

```

Create as many SectionAdapter you need :

```java
class MovieAdapter extends SectionAdapter {
    
}

```


Create a class which inherit NestedSectionAdapter :

```java
class UpcomingNestedOrderAdapter extends NestedSectionAdapter {

    public void setMovies(List<Movie> movies) {
        
        notifyDataSetChanged();
    }
}

```


# Objectives

- Improve the recycling of similar items
- Improve the notifyDataSetChanged