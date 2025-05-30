package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandException;
import Exceptions.CommandPositiveException;

public class UpdateIdCommand implements Command {
    @Override
    public String execute(String args, Context context) throws CommandException {

        long curId;
        int oldId = context.getDatabaseMovies().getCollection().size();
        //проверка всех исключений
        try {
            curId = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new CommandException("id должно быть натуральным числом.");
        }
        if (curId < 1) {
            throw new CommandPositiveException("id");
        }
        if (context.getDatabaseMovies().getCollection().stream().noneMatch(movie -> movie.getId() == curId)) {
            throw new CommandException("Элемента с таким id не существует.");
        }

        new RemoveByIdCommand().execute(args, context);
        new AddCommand().execute("", context);

        //обновляем айдишник у только что созданного элемента
        context.getDatabaseMovies().getCollection().get(oldId - 1).setId(curId);
        //делаем так, чтобы не было пропуском айдишников
        context.getDatabaseMovies().setLastMaxId(oldId);

        return "значение " + curId + " было обновлено.";
    }

    @Override
    public String getDescription() {
        return "обновляет значения в заданном элементе по id";
    }

    @Override
    public String getName() {
        return "update_id {index}";
    }
}
