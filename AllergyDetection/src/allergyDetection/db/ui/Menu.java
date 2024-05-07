package allergyDetection.db.ui;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import allergyDetection.db.*;
import allergyDetection.db.interfaces.*;
import allergyDetection.db.jdbc.ConnectionManager;
import allergyDetection.db.jdbc.JDBCPatientManager;
import allergyDetection.db.jdbc.*;
import allergyDetection.db.pojos.*;



public class Menu {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static ConnectionManager conMan;
	private static AllergyManager allergyManag;
	private static DoctorManager doctorManag;
	private static PatientManager patientManag;
	private static PrescriptionManager prescriptionManag;
	private static SymptomManager symptomManag;
	private static TreatmentManager treatmentManag;
	 
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("WELCOME TO THE PROGRAMM!");
		// ALWAYS JDBC first
		conMan = new ConnectionManager();
		patientManag = conMan.getPatient();
		doctorManag = conMan.getDoctor();
		allergyManag= conMan.getAllergy();
		symptomManag= conMan.getSymptom();
		treatmentManag= conMan.getTreatment();
		prescriptionManag= conMan.getPrescription();
	}
		// JAP later
			
	/*System.out.println("Chose your desired option: ");
	System.out.println("1) Log in. ");
	System.out.println("2) Sign up.");
	System.out.println("0) End the program.");
		int variableWhileInitial=1;
		while (variableWhileInitial!=0) {
	        int option = Integer.parseInt(r.readLine());
	        switch (option) {
	            case 1:
	                menuLogin();
	                break;
	            case 2:
	                menuSignUp();
	                break;
	            case 0:
	                System.out.println("Exiting the programm...");
	                variableWhileInitial=0;
	                conMan.close();
	                return;
	            default:
	                System.out.println("Insert one of the following options:");
	        }
		}
		
/*		
	private static void menuLogin() throws NumberFormatException, IOException {
		//TODO method 
	}
	
	private static void menuSignUp() throws NumberFormatException, IOException {
		//TODO method
	}
	
	
	
	public static void  menuPatient() throws NumberFormatException, IOException{
		System.out.println("Welcome patient! Select the option: ");
		int variableWhilePatient=1;
		while(variableWhilePatient!=0) {
		System.out.println("1) CHECK YOUR MEDICAL SCORE");
		System.out.println("2) SHOW PRESCRIPTION");
		System.out.println("0) EXIT");
		int choicePatient = Integer.parseInt(r.readLine());
		switch (choicePatient) {
		
		case 1: 
			//checkMedicalScore();
			//TODO the method. This method can be done here 
			break;
		
		case 2: 
			//showPrescription();
			//TODO the method. This method can be done here 
			break;
		
		case 0:
			variableWhilePatient=0;
			conMan.close();	
			break;
		
		default:
			System.out.println("You inserted a number not accepted. Please, select again a number of the following options");
		}
		
		}

	}
	
	//Patients methods.

public static void showPrescription() throws IOException, NumberFormatException{
		try {
			System.out.println("Here is your prescription:\n");
			JDBCPrescriptionManager.getPrescriptionsById(id);
            // Fetch prescriptions by patient ID
            List<Prescription> prescriptions = getPrescriptionsById(id); 

            if (prescriptions.isEmpty()) {
                System.out.println("No prescriptions found for the given ID.");
            } else {
                System.out.println("Prescriptions for patient with ID " + Patient.getId() + ":");
                for (Prescription prescription : prescriptions) {
                    System.out.println(prescription);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid integer ID.");
        }
    }


  	
*/
		
	public static void menuDoctor() throws NumberFormatException, IOException {
		int variableWhileDoctor=1;
		System.out.println("Welcome Doctor! We are delighted with your great job!");
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
		
	}}
/*	
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
	
	
	
	//method of lists in case we need.
	private static void listPrescriptions() throws IOException{
		System.out.println("Enter the prescription id");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("These are the available prescriptions, choose one by typing their id:");
		List<Prescription> prescription = PrescriptionManager.getPrescriptionById(id);
		//TODO  the method getPrescriptionById
		
		System.out.println(prescription);
	}
	private static void listAllergies() throws IOException{
		System.out.println("Enter the Allergy id");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("These are the available allergies, choose one by typing their id:");
		List<Allergy> allergies = AllergyManager.getAllergyById(id);
		//TODO  the method getAllergyById
		
		System.out.println(allergies);
	}
	private static void listSymptoms() throws IOException{
		System.out.println("Enter the Symptom id");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("These are the available symptoms, choose one by typing their id:");
		List<Symptom> symptoms = SymptomManager.getSymptomById(id);
		//TODO  the method getSymptomById
		
		System.out.println(symptoms);
	}
	
	
	
	
		
	
		
	
		
		
		
		
		

	
	
	
	
	
}


//the code that we should add to addPatient if we add the methods lists
/*		listPrescriptions();
Integer prescriptionId = Integer.parseInt(r.readLine());
Prescription prescrip= PrescriptionManager.getPrescription(prescriptionId);
listAllergies();
Integer allergyId = Integer.parseInt(r.readLine());
Allergy allergy= AllergyManager.getAllergy(allergyId);
System.out.println("To stop adding allergies, please add a (-1): ");
while(allergyId!=-1) {
	allergyId = Integer.parseInt(r.readLine());
	Allergy allergy1= AllergyManager.getAllergy(allergyId);
	System.out.println("A new allergy was added. ");
	System.out.println("To stop adding allergies, please add a (-1): ");
}
listSymptoms();
Integer symptomId = Integer.parseInt(r.readLine());
Symptom symptom= SymptomManager.getSymptom(symptomId);
System.out.println("To stop adding Symptoms, please add a (-1): ");
while(symptomId!=-1) {
	symptomId = Integer.parseInt(r.readLine());
	Symptom symptom1= SymptomManager.getSymptom(symptomId);
	System.out.println("A new symptom was added. ");
	System.out.println("To stop adding symptoms, please add a (-1): ");
}		*/

//............................................................





