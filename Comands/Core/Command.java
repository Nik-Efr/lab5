package Comands.Core;

import Classes.Context;
import Exceptions.CommandException;


public interface Command {

    public String execute(String args, Context context) throws CommandException;
    public String getDescription();
    public String getName();
}
