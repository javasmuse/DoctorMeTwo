package app;

import java.util.Arrays;

public class Commands {

    public static void handleCommand(String command) {
        // Get args and set flag
        boolean argsFound = false;
        String[] args = command.split("\\s+");
        String firstCommand = args[0];
        int numArgs = args.length - 1;
        if ( numArgs > 0){
            System.out.println("Entered args: " + Arrays.toString(args));
            argsFound = true;
        }
        // Send to appropriate command
        switch(firstCommand){
            //TODO Handle synonyms for the commands
            case "help":
                System.out.println("Some help with instructions");
                break;
            case "move":
                //TODO Also handle 'walk' or 'go' etc.
                System.out.println("Moving somewhere");
                break;
            case "get":
                System.out.println("Getting an item");
                break;
            case "hint":
                System.out.println("Giving you a hint");
                break;
            default:
                System.out.println("Bad command entered");
                break;
        }
    }

    private void help() {

    }

    private void get(String item) {

    }

    private void hint() {

    }

}
