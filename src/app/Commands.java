package app;

import java.util.Arrays;
import java.util.List;

public class Commands {

    public static void handleCommand(String command, String location) {
        // Get args and set flag
        List<String> args = Arrays.asList(command.split("\\s+").clone());
        args.stream().map(String::toLowerCase);
        String firstCommand = args.get(0);

        try {
            handleValidCommand(args, firstCommand, location);

        }
        catch (ArrayIndexOutOfBoundsException e ){
            System.out.println("Please enter subsequent arguments for " + firstCommand);
            helpWithCommandUsage();
            // Need to get user input again
        }

    }

    private static void handleValidCommand(
            List<String> args,
            String firstCommand,
            String currentLocation
                                           ) {
        if(firstCommand.equals("help")){
            help(currentLocation);
        } else {
            // Send to appropriate command
            String commandArg = args.get(1);
            switch(firstCommand){
                //TODO Handle synonyms for the commands
                case "get":
                    get(commandArg);
                    break;
                case "move":
                    //TODO Also handle 'walk' or 'go' etc.
                    move(commandArg);
                    break;
                case "hint":
                    hint();
                    break;
                default:
                    System.out.println("Bad command entered");
                    break;
            }
        }
    }

    private static void help(String location) {
        System.out.println("Player asked for general help");
        System.out.println("Player is currently located at " + location);
    }

    private static void helpWithCommandUsage() {
        System.out.println("Valid commands are: move, hint, get <item>");
    }

    private static void get(String item) {
        System.out.println("Player asked to get a " + item);
    }

    private static void hint() {
        System.out.println("Player asked for a hint");
    }

    private static void move(String direction) {
        System.out.println("Player asked to move " + direction);
    }
}
