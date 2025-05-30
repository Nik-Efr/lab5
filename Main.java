import Classes.*;
import Exceptions.CommandException;

import java.io.IOException;


public class Main {
    public static void main(String[] args)  {
        String filename = "C:\\Users\\76090\\IdeaProjects\\lab5\\table.csv";
        if(args.length > 0) {
            filename = args[0];
        }
        Context context = new Context(filename);

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
