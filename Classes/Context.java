package Classes;

import Comands.CommandDatabase;
import Comands.Core.CommandInvoker;
import Console.CsvFileParser;
import Console.FileParser;
import lombok.Data;

import java.util.Scanner;

@Data
public class Context {

    private CommandInvoker commandInvoker = new CommandInvoker();
    private CommandDatabase commandDatabase = new CommandDatabase(commandInvoker);
    private DatabaseMovies databaseMovies = new DatabaseMovies();

    private FileParser<Movie> fileParser;
    private Scanner scanner = new Scanner(System.in);

    private boolean isInFile;

    public void universalOutput(String string) {
        if (!isInFile) {
            System.out.println(string);
        }
    }

    public Context(String filename) {
        fileParser = new CsvFileParser<>(filename, Movie.class);
    }
}
