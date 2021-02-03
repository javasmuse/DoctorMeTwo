package app;
import entities.Player;
import util.Colors;
import util.GameConstants;
import util.Output;

import java.util.*;


public class DoctorMeApp {

    // Game constants per instance
    private final int WINNING_POINTS_REQUIRED = 100;
    private final int HEALTHVALUE = 100;
    private final int DIFFICULTY = 50;

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        DoctorMeApp app = new DoctorMeApp();
        app.playDrMe();

    }

    public void playDrMe() throws InterruptedException {
        Output.printColor("Hello! What is you name? \n>> ", Colors.ANSI_YELLOW, false);
        String playerName = sc.nextLine().strip();
        // initialize player
        Player player = new Player(playerName);





        // Create the game object, passing in one player with "normal"
        // difficulty represented as 50
        Game game = new Game(player, DIFFICULTY);

        game.playIntroduction(playerName);
        // Read and Load Word XML file
        Commands command = new Commands();
        command.loadWordXMLfile();

        // Read in the XML file
        XMLController xmlc = new XMLController();
        game.play(WINNING_POINTS_REQUIRED, HEALTHVALUE, xmlc.readXML());



    }



    private String getUserInput(){
        System.out.println("Enter a command: ");
        return sc.nextLine().strip();
    }

}