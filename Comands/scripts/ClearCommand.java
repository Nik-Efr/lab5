package Comands.scripts;

import Classes.Context;
import Classes.Movie;
import Comands.Core.Command;
import Exceptions.CommandArgsNotAcceptedException;
import Exceptions.CommandException;

import java.util.ArrayList;
import java.util.List;


public class ClearCommand implements Command {

    @Override
    public String execute(String args, Context context) throws CommandException {
        if (!args.isBlank()) {
            throw new CommandArgsNotAcceptedException("clear");
        }
        List<Movie> movies = context.getDatabaseMovies().getCollection();
        for (Movie movie : new ArrayList<>(movies)) {
            context.getDatabaseMovies().getCollection().remove(movie);
        }
        return "Массив очищен.";
    }

    @Override
    public String getDescription() {
        return "команда очищения массива с данными.";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
