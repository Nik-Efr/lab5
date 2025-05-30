package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandException;
import Exceptions.CommandPositiveException;

public class InsertAtCommand implements Command {
    @Override
    public String execute(String args, Context context) throws CommandException {

        long curId;
        try {
            curId = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new CommandException("id должно быть натуральным числом.");
        }

        if (curId < 1) {
            throw new CommandPositiveException("id");
        }

        int indexToInsert = -1;
        for (int i = 0; i <  context.getDatabaseMovies().getCollection().size(); i++) {
            if (context.getDatabaseMovies().getCollection().get(i).getId() >= curId) {
                indexToInsert = i;
                break;
            }
        }

        if (indexToInsert == -1) {
            throw new CommandException("Не удалось найти элемент с id " + curId);
        }
        //сдвигаем на 1 вправо всё что с и после
        for (int i = indexToInsert; i <  context.getDatabaseMovies().getCollection().size(); i++) {
            if (context.getDatabaseMovies().getCollection().get(i).getId() >= curId) {
                context.getDatabaseMovies().getCollection().get(i).setId(context.getDatabaseMovies().getCollection().get(i).getId() + 1);  // Увеличиваем id каждого элемента, чьи id больше curId
            }
        }


        new AddCommand().execute("", context);

        context.getDatabaseMovies().getCollection().get(context.getDatabaseMovies().getCollection().size() - 1).setId(curId);

        return "Объект добавлен в коллекцию по id = " + curId;

    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в заданную позицию";
    }

    @Override
    public String getName() {
        return "insert_at {index}";
    }
}
