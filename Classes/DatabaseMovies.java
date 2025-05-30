package Classes;


import lombok.Getter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;


@Getter
public class DatabaseMovies implements Database<Movie> {
    ArrayList<Movie> collection = new ArrayList<>();
    private static long lastMaxId = 0;

    public void setLastMaxId(long id) {
        lastMaxId = id;
    }

    public boolean addElementToDatabase(Movie h) {
        lastMaxId += 1;
        h.setId(lastMaxId);
        return collection.add(h);
    }


    @Override
    public boolean removeByID(long id) {
        return collection.removeIf(movie -> movie.getId() == id);
    }


    public void addAllElements(Collection<Movie> collection) {
        this.collection.addAll(collection);

        collection.stream()
                .max(Comparator.comparingLong(Movie::getId))
                .ifPresentOrElse(movie -> {
                    lastMaxId = movie.getId();
                }, () -> {});
    }
}
