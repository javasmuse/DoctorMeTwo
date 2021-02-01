package app;

import java.util.Arrays;
import java.util.List;

public class Commands {

    public static void handleCommand(String command) {
        // Get args and set flag
        boolean argsFound = false;
        List<String> args = Arrays.asList(command.split("\\s+").clone());
        args.stream().map(String::toLowerCase);
        String firstCommand = args.get(0);
        int numArgs = args.size() - 1;
        if ( numArgs > 0){
            System.out.println("Entered args: " + args.toString());
            argsFound = true;
        }

        try {
            handleValidCommand(argsFound, args, firstCommand);

        }
        catch (ArrayIndexOutOfBoundsException e ){
            System.out.println("Please enter subsequent arguments for " + firstCommand);
            help();
            // Need to get user input again
        }

    }

    private static void handleValidCommand(boolean argsFound, List<String> args, String firstCommand) {
        if(firstCommand.equals("help")){
            help();
        } else {
            // Send to appropriate command
            switch(firstCommand){
                //TODO Handle synonyms for the commands
                case "get":
                    get(args.get(1), argsFound);
                    break;
                case "move":
                    //TODO Also handle 'walk' or 'go' etc.
                    System.out.println("Moving somewhere");
                    break;

                case "hint":
                    System.out.println("Giving you a hint");
                    break;
                default:
                    System.out.println("Bad command entered");
                    break;
            }
        }
    }

    private static void help() {
        System.out.println("Player asked for help");
    }

    private static void get(String item, boolean argFound) {
        System.out.println("Player asked for help");


    }

    private static void hint() {
        System.out.println("Player asked for a hint");

    }

}
