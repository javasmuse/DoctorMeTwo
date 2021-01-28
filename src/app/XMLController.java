package app;

import entities.Threat;

import java.util.Collection;


public class XMLController {

    private String xmlFileName;

    public XMLController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    // read xml, not sure if this is needed, maybe just use as private
    public void readXML(String fileName){
        // push into collection
    }


    // Parse entities
    public Collection<Threat> parseXML(){
        //readXML(this.filename)
        return null;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }



}
