package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandArgsAcceptedException;
import Exceptions.CommandException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;

public class ExecuteScript implements Command {
    HashSet<Path> hashSet = new HashSet<>();

    @Override
    public String execute(String args, Context context) throws CommandException {
        try (FileReader fileReader = new FileReader(args)) {
            Path path = Path.of(args); // получаем адрес файла
            if (!hashSet.add(path)) {
                throw new CommandException("Рекурсивные команды не поддерживаются.");
            }

            try {
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNext()) {
                    String a = scanner.nextLine();
                    if (a.startsWith("#") || a.isBlank()) { //продолжтть выполнение если есть строчки
                        continue;
                    }
                    context.getCommandInvoker().executeCommand(a, context);
                }
            } catch (CommandException e) {
                hashSet.remove(path);
                throw e;
            } finally {
                hashSet.remove(path);
            }

        } catch (IOException e) {
            throw new CommandException("неправильное имя файла.");
        }
        return "Скрипт исполнен.";
    }

    @Override
    public String getDescription() {
        return "исполняет скрипт из файла";
    }

    @Override
    public String getName() {
        return "execute_script {filepath}";
    }
}
