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
		System.out.println("");
		
		
		//MenuDoctor.menuDoctor();
		//MenuPatient.menuPatient();
		
		// ALWAYS JDBC first
		conMan = new ConnectionManager();
		patientManag = conMan.getPatient();
		doctorManag = conMan.getDoctor();
		allergyManag= conMan.getAllergy();
		symptomManag= conMan.getSymptom();
		treatmentManag= conMan.getTreatment();
		prescriptionManag= conMan.getPrescription();
	
	
	
	//------------------------------------------------------------------------
		// JAP later
			
	System.out.println("Chose your desired option: ");
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
	}	
		
	private static void menuLogin() throws NumberFormatException, IOException {
		System.out.println("Introduce your username : ");
		System.out.println("Introduce your password : ");
		
	}
	
	private static void menuSignUp() throws NumberFormatException, IOException {
		System.out.println("Chose your desired option: ");
		System.out.println("1) Menu Doctor. ");
		System.out.println("2) Menu Patient. ");
		System.out.println("0) End the program. ");
		int variableWhileInitial=1;
		while (variableWhileInitial!=0) {
	        int option = Integer.parseInt(r.readLine());
	        switch (option) {
	            case 1:
	            	MenuDoctor.menuDoctor();
	                break;
	            case 2:
	            	MenuPatient.menuPatient();
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
	}
	
	
	


	
	//............................................................


	
	}
/*	
	
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





