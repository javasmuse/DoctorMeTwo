package app;

public class Commands {

    public static void handleCommand(String command) {
        // Send to appropriate command
        switch(command){
            case "help":
                System.out.println("Some help with instructions");
                break;
            case "move":
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
