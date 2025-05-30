package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandArgsNotAcceptedException;
import Exceptions.CommandException;



public class ClearCommand implements Command {

    @Override
    public String execute(String args, Context context) throws CommandException {
        if (!args.isBlank()) {
            throw new CommandArgsNotAcceptedException("clear");
        }
        context.getDatabaseMovies().getCollection().clear();

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
