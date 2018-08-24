package com.epam.parsers;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XSDValidate  {
    private String xmlPath;
    private String xsdPath;

    public boolean validateXML(){
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new File(xsdPath))
                    .newValidator()
                    .validate(new StreamSource(new File(xmlPath)));
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public XSDValidate(String xmlPath, String xsdPath) {
        this.xmlPath = xmlPath;
        this.xsdPath = xsdPath;
    }
}
