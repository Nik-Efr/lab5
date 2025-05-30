package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandArgsNotAcceptedException;
import Exceptions.CommandException;
import static java.util.Collections.shuffle;

public class ShuffleCommand implements Command {

    @Override
    public String execute(String args, Context context) throws CommandException {
        try {
            if (!args.isBlank()) {
                throw new CommandArgsNotAcceptedException("shuffle");
            }
            shuffle(context.getDatabaseMovies().getCollection());
        } catch (CommandArgsNotAcceptedException e) {
            throw new CommandException("Неправильный аргумент. Введите целое число.");        }
            return "Коллекция перемешана";
    }

    @Override
    public String getDescription() {
        return "перемешивает элементы коллекции в случайном порядке";
    }

    @Override
    public String getName() {
        return "shuffle";
    }
}
