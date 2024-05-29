package allergyDetection.db.xml;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import allergyDetection.db.interfaces.PatientManager;
import allergyDetection.db.interfaces.SymptomManager;
import allergyDetection.db.interfaces.XMLManager;
import allergyDetection.db.jdbc.ConnectionManager;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Symptom;

public class XMLManagerImplementation implements XMLManager {
	

	@Override
	public File patient2XML(Patient p) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		File file = new File("./xmls/Sample-Patient.xml");
		marshaller.marshal(p, file);
		marshaller.marshal(p, System.out);
		return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Patient XML2patient(File Fxml) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	Patient p = (Patient) unmarshaller.unmarshal(Fxml);
    	return p;
   
	}catch (Exception e) {
		System.out.println("ERROR: Unable to load XML file");
		e.printStackTrace();
		return null;
	}
	}
	
	@Override
	public void patient2Html(Patient p) {
		File f= patient2XML(p);
		TransformerFactory tFactory= TransformerFactory.newInstance();
		try {
			Transformer transformer=tFactory.newTransformer(new StreamSource(new File("./xmls/Patient-Style.xslt")));
			transformer.transform(new StreamSource(f), new StreamResult (new File("./xmls/External-Patient.html")));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public File doctor2XML(Doctor d) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		File file = new File("./xmls/Sample-Doctor.xml");
		marshaller.marshal(d, file);
		marshaller.marshal(d, System.out);
		return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Doctor XML2doctor(File Fxml) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	Doctor d = (Doctor) unmarshaller.unmarshal(Fxml);
    	return d;
   
	}catch (Exception e) {
		System.out.println("ERROR: Unable to load XML file");
		e.printStackTrace();
		return null;
	}
	}
	
	@Override
	public void doctor2Html(Doctor d) {
		File f= doctor2XML(d);
		TransformerFactory tFactory= TransformerFactory.newInstance();
		try {
			Transformer transformer=tFactory.newTransformer(new StreamSource(new File("./xmls/Doctor-Style.xslt")));
			transformer.transform(new StreamSource(f), new StreamResult (new File("./xmls/External-Doctor.html")));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
