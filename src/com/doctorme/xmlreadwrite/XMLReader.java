package com.doctorme.xmlreadwrite;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XMLReader {

    // call and pass in the XML doc and node name to return a node list of the xml
    public NodeList readXMLFiles(InputStream is, String nodNam){

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
//            File inputFile = new File(String.valueOf(is));
            Document doc = db.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(nodNam);
            return nodeList;
        }
        catch (javax.xml.parsers.ParserConfigurationException parseE) {
            System.out.println(parseE);
        } catch (java.io.IOException ioE) {
            System.out.println(ioE);
        } catch (org.xml.sax.SAXException saxException) {
            System.out.println(saxException);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}