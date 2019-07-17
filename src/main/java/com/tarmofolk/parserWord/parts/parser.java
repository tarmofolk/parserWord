package com.tarmofolk.parserWord.parts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class parser {

    public static void parseFile(Document doc) {
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        startCycle(root);
    }

    static void startCycle(Node start) {
        // Output to console for testing
        //System.out.println(start.getNodeName()+" = "+start.getNodeValue());
        if (start.getNodeType() == Node.ELEMENT_NODE) {

        } else {
            setNodeValue(start);
        }
        for (Node child = start.getFirstChild();
             child != null;
             child = child.getNextSibling()){
            startCycle(child);
        }
    }

    static void setNodeValue(Node target) {
        target.setNodeValue(processText(target.getNodeValue()));
    }

    static String processText(String input) {
        String result = input
                .replaceAll("(0[1-9]|[12][0-9]|3[01])[- /.]([а-яА-ЯёЁa-zA-Z0-9]+)[- /.](19|20)\\d\\d( года| г\\.)*", "document_date")
                .replaceAll("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d", "document_date")
                .replaceAll("[N№][- /.]([а-яА-ЯёЁa-zA-Z0-9\\-\\/]+)", "document_number");
        return result;
    }
}
