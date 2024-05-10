package allergyDetection.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.interfaces.PatientManager;
import allergyDetection.db.*;
import allergyDetection.db.pojos.Patient;

public class MenuDoctor {
	
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static PatientManager patientManag;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	
	public static void menuDoctor() throws NumberFormatException, IOException {
		int variableWhileDoctor=1;
		System.out.println("Welcome Doctor! We are delighted with your great job!");
		System.out.println("");
		while(variableWhileDoctor !=0) {
		System.out.println("Select the option desire: ");
		System.out.println("1) ADD A PATIENT TO THE DATA BASE");
		System.out.println("2) DELETE A PATIENT FROM THE DATA BASE");
		System.out.println("3) MODIFY THE INFORMATION OF A PATIENT"); // Remember that the patient must be registered first.");
		//System.out.println("4) See the patient medical score");		//it was said "patient records" are we looking for the same?
		System.out.println("5) Add the symptoms of a patient");
		System.out.println("6) Write a prescription for a patient. Remember that the patient must be registered first.");
		System.out.println("7) Modify the prescription of a patient.Remember that the patient and the prescription must be created first.");
		System.out.println("0) Select this option to exit.");
	
		int choiceDoctor = Integer.parseInt(r.readLine());
		switch (choiceDoctor) {
		case 1: 
			addPatient();
			break;
		
		case 2: 
			deletePatient();
			break;
		
		case 3: 
			modifyPatient();
			break;
		/*
		case 4: 
			//showMedicalScore();
			//TODO the method. This method can be done here 

			break;
			
		case 5: 
			addSymptom();
			

			break;	
		case 6: 
			addPrescription();
			

			break;
			
		case 7: 
			//modifyPrescription();
			//TODO the method. This method can be done here 

			break;
		
		case 0:
			variableWhileDoctor=0;
			conMan.close();			
		break;
		
		default:
			System.out.println("You inserted a number not accepted. Please, select again a number of the following options");
		
		*/}
		
		}
	}
	
	private static void addPatient()  throws NumberFormatException, IOException {
		System.out.println("Please, write the information of the patient:");
		System.out.println("PATIENT ID: ");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("PATIENT NAME: ");
		String name = r.readLine();
		System.out.println("DATE OF BIRTH OF THE PATIENT(DD-MM-YYYY format): ");
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date date = Date.valueOf(localDate);
		System.out.println("PATIENT GENDER: ");
		String gender = r.readLine();
		Patient patient = new Patient(id,name,date,gender);		
		patientManag.addPatient(patient);
	}	
	
	private static void deletePatient()  throws NumberFormatException, IOException {
		System.out.println("You will delete a patient from the data base information. ");
		List<Patient> patients = new ArrayList<Patient>();
		System.out.println("Introduce the name of the patient you want to select:");
		String Name = r.readLine();
		patients= patientManag.searchPatient(Name);
		for (Patient p : patients) {
			System.out.println(p);
		}
		
	    System.out.println("Introduce the PATIENT ID TO DELETE");
		Integer Id = Integer.parseInt(r.readLine());
		patientManag.deletePatient(Id);
	}
	
	private static void modifyPatient() throws NumberFormatException, IOException {
		// SHOW THE LIST OF PATIENTS , HERE YOU CAN SELECT THE PATIENT INSERT ID
		List<Patient> patients = new ArrayList<Patient>();
		Patient p_empty=null;
		System.out.println("Introduce the name of the patient you want to select:");
		String Name = r.readLine();
		patients= patientManag.searchPatient(Name);
		for (Patient p : patients) {
			System.out.println(p);
		}
		
	    System.out.println("Introduce the PATIENT ID TO MODIFY");
		Integer Id = Integer.parseInt(r.readLine());
		Patient p=patientManag.getPatientByID(Id);
		System.out.println("Here are the actual author's values");
		System.out.println("Press enter to keep them or type a new value.");
		System.out.println("Name (" + p.getName() + "): ");
		String newName = r.readLine();
		System.out.println("Gender (" + p.getGender() + "): ");
		String newGender = r.readLine();
		if (newName== p.getName()) {
			p_empty.setName(p.getName());
		}
		else { 
            p_empty.setName(newName);
		}
		if (newGender== p.getGender()) {
			p_empty.setGender(p.getGender());
		}
		else { 
			p_empty.setGender(newGender);
		}
		patientManag.modifyPatient(p_empty);
		
	}
	}

// --------------------------------------------------------
// Aquí abajo pongo los otros métodos comentados.
/*
private static void addSymptom()  throws NumberFormatException, IOException {
	System.out.println("Please, write the information of the symptom:");
	System.out.println("Patient id: ");
	Integer id = Integer.parseInt(r.readLine());
	System.out.println("Symptom name: ");
	String name = r.readLine(); 
	System.out.println("Symptom type: ");
	String type = r.readLine();
	
	Symptom symptom = new Symptom(id,name,type);			
	symptomManag.addSymptom(symptom);
	}
	
	
	
	
	private static void addPrescription()  throws NumberFormatException, IOException {
		System.out.println("Please, write the information of the patient and the doctor:");
		System.out.println("Treatment name: ");
		String treatmentName = r.readLine(); // for patient and doctor we need to call method getDoctorid and same w/ patient
		System.out.println("Patient id: ");
		Integer idPatient = Integer.parseInt(r.readLine());
		System.out.println("Doctor id: ");
		Integer idDoctor = Integer.parseInt(r.readLine());
		
		Prescription prescription = new Prescription(treatmentName, idPatient,idDoctor);			
		prescriptionManag.addPrescription(prescription);
	}
	
	*/

