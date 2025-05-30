package Comands.scripts;

import Classes.Context;
import Classes.Movie;
import Comands.Core.Command;
import Exceptions.CommandException;

import java.util.Objects;

import static java.lang.Math.round;

public class AverageOfOscarsCount implements Command {
    @Override
    public String execute(String args, Context context) throws CommandException {
        try {

            double answer = context.getDatabaseMovies().getCollection()
                    .stream() //делаем поток Stream<Movie>
                    .map(Movie::getOscarsCount) //создаём мапу из оскаров
                    .filter(Objects::nonNull) // удаляем все нули из мапы
                    .mapToInt(Integer::intValue)// превращаем все элементы из мапы в инты
                    .average()
                    .orElse(0);

            return "Среднее количество Оскаров в фильмах коллекции " + round(answer) + ".";
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
    }


    @Override
    public String getDescription() {
        return "выводит среднее значение поля oscarsCount для всех элементов коллекции";
    }

    @Override
    public String getName() {
        return "average_of_oscars_count";
    }
}
