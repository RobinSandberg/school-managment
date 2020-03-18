package se.lexicon.robin;

import se.lexicon.robin.data_access.CourseDaoList;
import se.lexicon.robin.data_access.StudentDaoList;
import se.lexicon.robin.user_interface.CommandLine;
import se.lexicon.robin.user_interface.CommandLineFunctions;
import se.lexicon.robin.user_interface.UserInterface;

import java.util.Iterator;

public class App
{
    public static void main( String[] args )
    {
        UserInterface userInterface = new UserInterface();
        userInterface.menuOptions();
    }
}
