package app;

import java.io.File;

import entities.Player;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.Scanner;


public class DoctorMeApp{
    private Player player;

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        //TODO Delegate this behavior to Game Class
        DoctorMeApp app = new DoctorMeApp();
        app.playDrMe();

        //Game game = new Game();
        //game.start()

    }

    public void playDrMe() {
        System.out.println("Hello model.Player, What is you name?");
        String playerName = sc.nextLine();

        System.out.println("Test: the players name is:  " + playerName);


        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File inputFile = new File("resources/test.xml");
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root Element:  " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("disease");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Question: " + eElement.getElementsByTagName("question").item(0).getTextContent());
                    System.out.println("Points: " + eElement.getElementsByTagName("points").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void into(){

    }

    public void getUserInput(){

    }

    public void getXML(){

    }

}