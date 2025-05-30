package Comands;

import Comands.Core.CommandInvoker;
import Comands.scripts.*;


public class CommandDatabase {
    public CommandDatabase(CommandInvoker commandInvoker) {
        commandInvoker.commandDictionaryPut("exit", new ExitCommand());
        commandInvoker.commandDictionaryPut("help", new HelpCommand());
        commandInvoker.commandDictionaryPut("show", new ShowCommand());
        commandInvoker.commandDictionaryPut("remove_by_id", new RemoveByIdCommand());
        commandInvoker.commandDictionaryPut("add", new AddCommand());
        commandInvoker.commandDictionaryPut("clear", new ClearCommand());
        commandInvoker.commandDictionaryPut("info", new InfoCommand());
        commandInvoker.commandDictionaryPut("execute_script", new ExecuteScript());
        commandInvoker.commandDictionaryPut("average_of_oscars_count", new AverageOfOscarsCount());
        commandInvoker.commandDictionaryPut("filter_by_genre", new FilterByGenreCommand());
        commandInvoker.commandDictionaryPut("save", new SaveCommand());
        commandInvoker.commandDictionaryPut("update_id", new UpdateIdCommand());
        commandInvoker.commandDictionaryPut("remove_lower", new RemoveLowerCommand());
        commandInvoker.commandDictionaryPut("insert_at", new InsertAtCommand());
        commandInvoker.commandDictionaryPut("shuffle", new ShuffleCommand());
        commandInvoker.commandDictionaryPut("filter_starts_with_name", new FilterStartsWithNameCommand());

    }

}
