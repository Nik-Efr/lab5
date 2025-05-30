package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandArgsNotAcceptedException;
import Exceptions.CommandException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class InfoCommand implements Command {
    @Override
    public String execute(String args, Context context) throws CommandException {
        if (!args.isBlank()) {
            throw new CommandArgsNotAcceptedException("info");
        }
        StringBuilder builder = new StringBuilder();

        builder.append("Тип коллекции: ").append(context.getDatabaseMovies()
                .getCollection()
                .getClass().getSimpleName());

        builder.append("\nДата инициализации: ");

        try {
            BasicFileAttributes attributes = Files.readAttributes(Path.of(context.getFileParser().getFileName()), BasicFileAttributes.class);

            builder.append(attributes.creationTime());
        } catch (IOException e) {
            throw new CommandException("Файл повреждён.");
        }
        builder.append("\nКоличество элементов: ").append(context.getDatabaseMovies().getCollection().size());

        return builder.toString();

    }

    @Override
    public String getDescription() {
        return "выводит информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }

}
