package com.tarmofolk.parserWord;

import com.tarmofolk.parserWord.parts.reader;
import com.tarmofolk.parserWord.parts.parser;
import com.tarmofolk.parserWord.parts.writer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
            Document doc = reader.inputFile();
            parser.parseFile(doc);
            writer.writeFile(doc);
        }
}