package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandArgsNotAcceptedException;
import Exceptions.CommandException;

public class ShowCommand implements Command {

    @Override
    public String execute(String args, Context context) throws CommandException {
        if (!args.isBlank()) {
            throw new CommandArgsNotAcceptedException("show");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object s : context.getDatabaseMovies().getCollection()) {
            stringBuilder.append(s).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String getDescription() {
        return "показывает коллекцию со всеми записями";
    }

    @Override
    public String getName() {
        return "show";
    }
}
