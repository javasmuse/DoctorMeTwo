package app;

import entities.Threat;

import java.util.List;

public class XMLController {



    private String xmlFileName;

    public XMLController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    // read xml, not sure if this is needed, maybe just use as private
    public void readXML(String fileName){

    }


    // Parse entities
    public List<Threat> parseXML(){
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
