package allergyDetection.db.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import allergyDetection.db.xml.utils.CustomErrorHandler;

public class DTDCheckerReport {
	public static void main(String[] args) {
        File xmlFile = new File("./xmls/External-Doctor.xml"); 
        try {
        	// Create a DocumentBuilderFactory
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            // Set it up so it validates XML documents
            dBF.setValidating(true);
            // Create a DocumentBuilder and an ErrorHandler (to check validity)
            DocumentBuilder builder = dBF.newDocumentBuilder();
            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
            builder.setErrorHandler((ErrorHandler) customErrorHandler);
            // Parse the XML file and print out the result
            Document doc = builder.parse(xmlFile);
            if (customErrorHandler.isValid()) {
                System.out.println(xmlFile + " was valid!");
            }
        } catch (ParserConfigurationException ex) {
            System.out.println(xmlFile + " error while parsing!");
        } catch (SAXException ex) {
            System.out.println(xmlFile + " was not well-formed!");
        } catch (IOException ex) {
            System.out.println(xmlFile + " was not accesible!");
        }

        File xmlFile2 = new File("./xmls/External-Patient.xml"); 
        try {
        	// Create a DocumentBuilderFactory
            DocumentBuilderFactory dBF2 = DocumentBuilderFactory.newInstance();
            // Set it up so it validates XML documents
            dBF2.setValidating(true);
            // Create a DocumentBuilder and an ErrorHandler (to check validity)
            DocumentBuilder builder2 = dBF2.newDocumentBuilder();
            CustomErrorHandler customErrorHandler2 = new CustomErrorHandler();
            builder2.setErrorHandler((ErrorHandler) customErrorHandler2);
            // Parse the XML file and print out the result
            Document doc2 = builder2.parse(xmlFile2);
            if (customErrorHandler2.isValid()) {
                System.out.println(xmlFile2 + " was valid!");
            }
        } catch (ParserConfigurationException ex) {
            System.out.println(xmlFile2 + " error while parsing!");
        } catch (SAXException ex) {
            System.out.println(xmlFile2 + " was not well-formed!");
        } catch (IOException ex) {
            System.out.println(xmlFile2 + " was not accesible!");
        }
    }

}
