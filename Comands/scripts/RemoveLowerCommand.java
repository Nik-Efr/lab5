package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandArgsAcceptedException;
import Exceptions.CommandException;


public class RemoveLowerCommand implements Command {
    @Override
    public String execute(String args, Context context) throws CommandException {
        if (args.isBlank()) {
            throw new CommandArgsAcceptedException("remove_lower");
        }
        try {
            long threshold = Long.parseLong(args);
            boolean anyRemoved = false;


            for (int i = context.getDatabaseMovies().getCollection().size() - 1; i >= 0; i--) {
                if (context.getDatabaseMovies().getCollection().get(i).getOscarsCount() != null && context.getDatabaseMovies().getCollection().get(i).getOscarsCount() < threshold) {
                    context.getDatabaseMovies().removeByID(context.getDatabaseMovies().getCollection().get(i).getId());
                    anyRemoved = true;
                }
            }

            if (anyRemoved) {
                return "Коллекция обновлена.";
            } else {
                return "Нет фильмов с количеством Оскаров меньше " + threshold + ".";
            }

//            return context.getDatabaseMovies()
//                    .getCollection()
//                    .stream()
//                    .filter(movie -> movie.getOscarsCount() != null && movie.getOscarsCount() > Long.parseLong(args) )
//                    .map(movie -> movie + "\n")
//                    .collect(Collectors.joining());
        } catch (NumberFormatException e) {
            throw new CommandException("Неправильный аргумент. Введите целое число.");
        }
    }


    @Override
    public String getDescription() {
        return "удаляет из коллекции все элементы, меньшие чем заданный";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }
}
