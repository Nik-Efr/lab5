import Classes.*;
import Exceptions.CommandException;

import java.io.IOException;


public class Main {
    public static void main(String[] args)  {

        Context context = new Context(args[0]);

        try {
            context.getDatabaseMovies().addAllElements(
                    context.getFileParser().deserializeFromFile()
            );
            context.getDatabaseMovies().getCollection().sort(Movie::compareTo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            String current = context.getScanner().nextLine();
            try {
                context.getCommandInvoker().executeCommand(current, context);
            } catch (CommandException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
