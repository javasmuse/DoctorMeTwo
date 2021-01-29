package app;

import java.io.File;

//import app.XMLController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.Scanner;



public class DoctorMeApp{
    private final String GAME_INTRODUCTION =
            "Doctor Me needs your help!\n" +
            "You are on a mission to save the body\n" +
            "Here are some tips on how to play...\n" +
            "Commands:\n" +
            "HELP - displays more information on commands\n" +
            "HINT - gives you a hint on what to do next\n" +
            "GET <item> - gets the item from your inventory to see if its useful\n" +
            "INVENTORY - displays the items you have";

    private final String GAME_INTRODUCTION_TWO =
            "The walls shake as the roar of borborygmus envelops the room.\n" +
            "As you look around, you notice the walls have many folds--rugae, perhaps?\n" +
            "The floor bubbling with acid, you realize you're in the stomach.\n" +
            "What discoveries await as you explore the inner-workings of the human body?";

    private int HEALTHVALUE = 100; // initiates health value
    public static final String BOLD_RED = "\033[1;91m";
    //private Player player;

    private StringBuilder sb = new StringBuilder();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DoctorMeApp app = new DoctorMeApp();
        app.playDrMe();

    }

    public void playDrMe() {

        System.out.println("Hello model.Player, What is you name?");
        String playerName = sc.nextLine().strip();
        // initialize player
        //player(String playerName, int HEALTHVALUE);

        // Display game introduction related information
        System.out.println("Hello welcome to Dr Me "+ BOLD_RED + playerName );
        System.out.println(GAME_INTRODUCTION);
        System.out.println(GAME_INTRODUCTION_TWO);
        XMLController xmlc = new XMLController();
        xmlc.readXML();

    }

//    public void into(){
//
//    }
//
//    public void getUserInput(){
//
//    }
//
//    public void getXML(){
//
//    }

}