package Comands.scripts;

import Classes.Context;
import Classes.Movie;
import Comands.Core.Command;
import Exceptions.CommandArgsNotAcceptedException;
import Exceptions.CommandException;

public class AddCommand implements Command {

    @Override
    public String execute(String args, Context context) throws CommandException {
        if (!args.isBlank()) {
            throw new CommandArgsNotAcceptedException("add");
        }
        Movie movie = Movie.createFromInput(context);
        if (context.getDatabaseMovies().addElementToDatabase(movie)) {
            return "Объект добавлен в коллекцию.";
        } else {
            return "Попробуйте ещё раз.";
        }
     }


    @Override
    public String getDescription() {
        return "добавляет новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}
