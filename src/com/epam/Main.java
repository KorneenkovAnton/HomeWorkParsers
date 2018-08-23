package com.epam;

import com.epam.entity.Employee;
import com.epam.parsers.DOMPars;
import com.epam.parsers.SAXPars;
import com.epam.parsers.StAXPars;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    static final String FILE_PATH = "src/com/epam/Employee.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        System.out.println("Sax method");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SAXPars saxPars = new SAXPars();
        xmlReader.setContentHandler(saxPars);
        xmlReader.parse(FILE_PATH);

        for (Employee empl : saxPars.getEmployees()){
            System.out.println(empl);
        }

        System.out.println("DOM method");

        DOMPars domPars = new DOMPars();
        for (Employee empl : domPars.getEmployeeList(FILE_PATH)){
            System.out.println(empl);
        }

        System.out.println("StAX method");

        StAXPars stAXPars = new StAXPars();
        for (Employee employee : stAXPars.getEmployeeList(FILE_PATH)){
            System.out.println(employee);
        }

    }
}
