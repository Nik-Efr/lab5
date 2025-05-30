package Comands.scripts;

import Classes.Context;
import Classes.MovieGenre;
import Comands.Core.Command;
import Exceptions.CommandArgsAcceptedException;
import Exceptions.CommandException;

import java.util.stream.Collectors;

public class FilterByGenreCommand implements Command {
    @Override
    public String execute(String args, Context context) throws CommandException {
        if (args.isBlank()) {
            throw new CommandArgsAcceptedException("filter_by_genre");
        }

        try {
            MovieGenre genre = MovieGenre.valueOf(args.toUpperCase()); //чтобы пользвоатель смог ввести значение в любом регистре
            return context.getDatabaseMovies()
                    .getCollection()
                    .stream()
                    .filter(movie -> movie.getGenre() == genre)
                    .map(movie -> movie + "\n")
                    .collect(Collectors.joining());
        } catch (IllegalArgumentException e) {
            throw new CommandException("Неправильный аргумент. Введите один из жанров: ACTION, TRAGEDY, THRILLER.");        }
    }
    @Override
    public String getDescription() {
        return "выводит поля с заданным жанром";
    }

    @Override
    public String getName() {
        return "filter_by_genre {genre_type}";
    }
}
