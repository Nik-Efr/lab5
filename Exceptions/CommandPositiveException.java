package Exceptions;

public class CommandPositiveException extends CommandException {
    public CommandPositiveException(String command) {
        super("Команда " + command + " принимает положительные аргументы.");
    }
}
