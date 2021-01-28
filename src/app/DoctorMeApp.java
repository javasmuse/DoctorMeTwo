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
    //private Player player;
    private int HEALTHVALUE = 100; // initiates health value
    public static final String BOLD_RED = "\033[1;91m";

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

        System.out.println("Hello welcome to Dr Me "+ BOLD_RED + playerName );
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