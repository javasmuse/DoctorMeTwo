package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.Colors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Commands {
    private static HashMap<String, List<String>> wordList = new HashMap<>();

    // Starting point of handling commands
    // Command is the raw user input
    // Location is the current player location according to the current turn
    public static boolean handleCommand(String command, String location) {
        // Get args and set flag
        List<String> args;
        args = Arrays.asList(command.split("\\s+").clone()).stream().map(String::toLowerCase)
                .collect(Collectors.toList());
        String firstCommand = args.get(0);

        try {
            // A valid command has been entered
            handleValidCommand(args, firstCommand, location);

        }
        catch (ArrayIndexOutOfBoundsException e ){
            System.out.println("Please enter subsequent arguments for " + firstCommand);
            helpWithCommandUsage();
            // TODO Continue logic and game loop to ask for input again
            return false;
        }

        return true;

    }

    // Handles commands only after they are validated
    private static void handleValidCommand(
            List<String> args,
            String firstCommand,
            String currentLocation
                                           ) {

        // Check for commands that don't have second command line arguments
        if(firstCommand.equals("help")){
            help(currentLocation);
        } else if(firstCommand.equals("hint")) {
            hint();
        } else {
            // Send to appropriate command and method
            handleMultipleArgumentCommand(args, firstCommand);
        }
    }

    // Handles commands specifically requiring one or more arguments
    // For example, "get" command requires an <item>
    // get <item>
    // get tcell
    // get first aid
    private static void handleMultipleArgumentCommand(List<String> args, String firstCommand) {
        try {
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
                default:
                    System.out.println("Bad command entered");
                    break;
            }
        } catch (IndexOutOfBoundsException e ){
            System.out.println("Please enter subsequent arguments for " + firstCommand);
            helpWithCommandUsage();
        }

    }

    private static void help(String location) {
        System.out.println("Player asked for general help");
        System.out.println("Player is currently located at " + location);
    }

    private static void helpWithCommandUsage() {
        System.out.println("Valid commands are: move, hint, get <item>" + Colors.ANSI_YELLOW);
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

    public static String wordMatch(String wordSent){
        String input = wordSent;
        String result="";
        for(Map.Entry<String, List<String>> entry : wordList.entrySet()){
            if(entry.getValue().contains(wordSent)){
                result = entry.getKey();
            }
            else {
                result= "No Match";
            }
        }
        return result;
    }

    public static void loadWordXMLfile(){
        List<String> tempArr = new ArrayList<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File inputFile = new File("resources/Word.xml");
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("verb");
            NodeList nodeList2 = doc.getElementsByTagName("synonym");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String action = eElement.getElementsByTagName("action").item(0).getTextContent();
                    String synonym = eElement.getElementsByTagName("synonym").item(0).getTextContent();
                    // TODO seperate synonym into pars breaking on comma, then add to tempArr, the put into HashMap
                    tempArr = Arrays.asList(synonym.split(","));
                    wordList.put(action, tempArr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // XXX this is a test to be removed
       // System.out.println(wordList+"  wordList TEST");

    }
}
