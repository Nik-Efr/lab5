package Comands.scripts;

import Classes.Context;
import Classes.Movie;
import Comands.Core.Command;
import Exceptions.CommandArgsAcceptedException;
import Exceptions.CommandException;

import java.util.stream.Collectors;

public class FilterStartsWithNameCommand implements Command {
    @Override
    public String execute(String args, Context context) throws CommandException {
        if (args.isBlank()) {
            throw new CommandArgsAcceptedException("filter_starts_with_name");
        }
        try {
            return context.getDatabaseMovies()
                    .getCollection()
                    .stream()
                    .filter(movie -> movie.getName().startsWith(args))
                    .map(Movie::toString)
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new CommandException("Неправильный аргумент. Введите строку.");
        }
    }

    @Override
    public String getDescription() {
        return "возвращает все элементы, значение поля name которых начинается с заданной подстроки";
    }

    @Override
    public String getName() {
        return "filter_starts_with_name {name}";
    }
}
