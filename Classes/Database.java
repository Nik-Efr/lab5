package Classes;

import java.util.Collection;


public interface Database<T> {

    Collection<T> getCollection();
    boolean addElementToDatabase(Movie h);

    boolean removeByID(long id);

    void addAllElements(Collection<T> collection);
}
