package Comands.scripts;

import Classes.Context;
import Comands.Core.Command;
import Exceptions.CommandArgsNotAcceptedException;

public class ExitCommand implements Command {

    @Override
    public String execute(String args, Context context) throws CommandArgsNotAcceptedException {
        if (!args.isBlank()) {
            throw new CommandArgsNotAcceptedException("exit");
        }
        System.exit(0);
        return null;
    }

    @Override
    public String getDescription() {
        return "выходит из консоли";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
