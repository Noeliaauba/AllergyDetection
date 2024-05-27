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

import allergyDetection.db.interfaces.PatientManager;
import allergyDetection.db.interfaces.SymptomManager;
import allergyDetection.db.interfaces.XMLManager;
import allergyDetection.db.jdbc.ConnectionManager;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Symptom;

public class XMLManagerImplementation implements XMLManager {
	
	private SymptomManager symptomManag;

	@Override
	public void patient2XML(Patient p) {
		try {
		int patient_id=p.getId();
		//COMO HACERLO CON SYMPTOMS y para guardarlos una vez cargados??
		List<Symptom> symptoms= symptomManag.searchSymptombyPatient(patient_id);
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		File file = new File("./xmls/Sample-Patient.xml");
		marshaller.marshal(p, file);
		marshaller.marshal(p, System.out);
		} catch (Exception e) {
			
		}
		
		
	}

	@Override
	public Patient XML2patient(File Fxml) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	Patient p = (Patient) unmarshaller.unmarshal(Fxml);
		System.out.println("Patient:");
		String name=p.getName();
		System.out.println("Name: " + name);
		String surname=p.getSurname();
		System.out.println("Surname: " +surname);
		Date dob= p.getDob();
		System.out.println("Date Of Birth: " + dob);
		String gender=p.getGender();
		System.out.println("Gender: " + gender);
		String username=p.getUsername();
		System.out.println("Username: " + username);
		List<Symptom> syms = p.getSymptoms();
				for (Symptom s: syms) {
					System.out.println("SYMPTOM DETECTED: " + s.getSymptom_name());
				}
		Patient pat= new Patient (name,surname,dob,gender,username);
        return pat;
		}catch (Exception e) {}
		return null;
	}
	
	/*
		// Store the report in the database
		// Create entity manager
				factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
				EntityManager em = factory.createEntityManager();
				em.getTransaction().begin();
				em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
				em.getTransaction().commit();

				// Create a transaction
				EntityTransaction tx1 = em.getTransaction();

				// Start transaction
				tx1.begin();

				// Persist
				// We assume the authors are not already in the database
				// In a real world, we should check if they already exist
				// and update them instead of inserting as new
				for (Employee employee : emps) {
					em.persist(employee);
				}
				em.persist(report);
				
				// End transaction
				tx1.commit();
	}
*/
	@Override
	public void patient2Html(Patient p) {
		// TODO Auto-generated method stub
		
	}
	

}
