package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandException;

import java.io.IOException;

public class SaveCommand implements Command {

    @Override
    public String execute(String args, Context context) throws CommandException {
        try {
            context.getFileParser().serializeIntoFile(context.getDatabaseMovies().getCollection().iterator());
            return "Объект сохранён.";
        } catch (IOException e) {
            throw new CommandException("Ошибка сохранения объекта.");
        }
    }
    @Override
    public String getDescription() {
        return "сохраняет коллекцию";
    }

    @Override
    public String getName() {
        return "save";
    }
}
