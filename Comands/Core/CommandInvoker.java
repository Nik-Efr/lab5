package Comands.Core;

import Classes.Context;
import Exceptions.CommandException;
import Exceptions.IncorrectCommand;
import lombok.Getter;

import java.util.HashMap;


@Getter
public class CommandInvoker {
    private final HashMap<String, Command> commandDictionary = new HashMap<>();

    public void executeCommand(String commandLine, Context context) throws CommandException {
        commandLine = commandLine.strip();
        String command = commandLine.split(" ", 2)[0];
        String args = ""; // области видимости

        if (!commandLine.equals(command)) {
            args = commandLine.split(" ", 2)[1];
        }
        if (commandDictionary.containsKey(command)) {
            System.out.println(commandDictionary.get(command).execute(args, context));
        } else {
            throw new IncorrectCommand("Неверная команда.");
        }

    }
    public void commandDictionaryPut(String s, Command c) {
        commandDictionary.put(s, c);
    }
}
