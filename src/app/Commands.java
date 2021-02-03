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
    private static String location;

    // Starting point of handling commands
    // Command is the raw user input
    // Location is the current player location according to the current turn

    public static boolean handleCommand(String command, String location) {
        // Get args and set flag
        List<String> args;
        List<String> task = new ArrayList<>();
        args = Arrays.asList(command.split("\\s+").clone()).stream().map(String::toLowerCase)
                .collect(Collectors.toList());
        //String firstCommand = args.get(0);
        if (args != null && args.size() > 0) {
            for (int i = 0; i < args.size(); i++) {
                task.add(args.get(i));
            }
        } else {
            System.out.println("Arguments sent into 'handleCommand' is NULL or EMPTY");
            return false;
        }

        try {
            // A valid command has been entered
            handleValidCommand(task, location);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter subsequent arguments for " + task);
            helpWithCommandUsage();
            // TODO Continue logic and game loop to ask for input again
            return false;
        }

        return true;

    }

    // Handles commands only after they are validated
    private static void handleValidCommand(List<String> task, String currentLocation) {
        // Check for commands that don't have second command line arguments
        location = currentLocation;
        if (task.contains("help")) {
            help(currentLocation);
        } else if (task.contains("hint")) {
            hint();
        } else {

            handleMultipleArgumentCommand(task); // Send to appropriate command and method
        }
    }

    // Handles commands specifically requiring one or more arguments
    // For example, "get" command requires an <item>
    // get <item>
    // get tcell
    // get first aid
    private static void handleMultipleArgumentCommand(List<String> task) {

       String wordmatch =  wordMatch(task); // this checks if what the player sent has a valid verb
         //task.remove(0);

        if(wordmatch == "no match"){
            System.out.println("That command is not valid");
            // TODO need to send player back where they came from
        } else {
            // XXX at this point the verb is seperated, now need to determine
            // if the other words in the list are good
        try {
            // TODO seperate verb from rest and send rest on

            //List<String> commandArg = task.remove(wordmatch) ;


            switch (wordmatch) {
                //TODO Handle synonyms for the commands
                case "get":
                    get(task);
                    break;
                case "move":
                    //TODO Also handle 'walk' or 'go' etc.
                    move(task);
                    break;
                default:
                    System.out.println("Bad command entered");
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter subsequent arguments for " + wordmatch);
            helpWithCommandUsage();
        }
        }
    }

    private static void help(String location) {
        System.out.println("Player asked for general help");
        System.out.println("Player is currently located at " + location);
    }

    private static void helpWithCommandUsage() {
        System.out.println("Valid commands are: move, hint, get <item>" + Colors.ANSI_YELLOW);
    }

    private static void get(List<String> item) {
        // TODO iterate and find
        System.out.println("Player asked to get a " + item);
    }

    private static void hint() {
        System.out.println("Player asked for a hint");
    }

    private static void move(List<String> item) {
        System.out.println("Player asked to move " + item + " to "+ location);
    }

    public static String wordMatch(List<String> wordsSent) {
        // String wordToTest = wordSent;
        String result = "";
        for (int i = 0; i < wordsSent.size(); i++) {
            String wordToTest = wordsSent.get(i);

            for (Map.Entry<String, List<String>> entry : wordList.entrySet()) {
                if (entry.getValue().contains(wordToTest)) {
                    result = entry.getKey();
                    // System.out.println(result+"  the Positive result is"); //For testing purposes
                    return result;

                }
            }
        }
        if (result.isEmpty()) {
            System.out.println(" no match");// will delete after testing
            result = "no match";
            return result;
        } else {
            return result;
        }
    }

    public static HashMap loadWordXMLfile() {
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
            System.out.println("An Error Occured while loading the synonym list.");
            e.printStackTrace();
        }
        // XXX this is a test to be removed
        // System.out.println(wordList+"  wordList TEST");

        return wordList;
    }
}
