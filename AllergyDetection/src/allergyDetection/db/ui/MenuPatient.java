package allergyDetection.db.ui;

import java.io.BufferedReader;


import allergyDetection.db.jdbc.ConnectionManager;
import allergyDetection.db.interfaces.*;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;

public class MenuPatient {
	
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

	private static PrescriptionManager prescriptionManager;
	private static PatientManager patientManag;
	private static SymptomManager symptomManag;
	private static AllergyManager allergyManag;
	private static ConnectionManager conMan;
	
	
	public static void  menuPatient(String username) throws NumberFormatException, IOException{
		conMan = new ConnectionManager();
		prescriptionManager =conMan.getPrescription();
		patientManag = conMan.getPatient();
		symptomManag= conMan.getSymptom();
		allergyManag= conMan.getAllergy();
		
		System.out.println("Welcome patient! Select the option: ");
		int variableWhilePatient=1;
		Patient p= patientManag.getPatientByusername(username);
		while(variableWhilePatient!=0) {
		System.out.println("1) CHECK YOUR MEDICAL SCORE");
		System.out.println("2) SHOW PRESCRIPTION");
		System.out.println("0) EXIT");
		int choicePatient = Integer.parseInt(r.readLine());
		switch (choicePatient) {
		
		case 1: 
			checkMedicalScore(p); 
			break;
		
		case 2: 
			showPrescriptions(p);
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
	
	
	public static void checkMedicalScore(Patient p)throws IOException, NumberFormatException {
		System.out.println("You will see your MEDICAL SCORE: ");
		int patientId=p.getId();
		List<Symptom> symptoms= symptomManag.searchSymptombyPatient(patientId);
		List<Allergy> allergies= allergyManag.searchAllergybyPatient(patientId);
		System.out.println("SYMPTOMS SAVED: ");
		for (Symptom s : symptoms) {
			System.out.println(s);
		}
		System.out.println("ALLERGIES DETECTED: ");
		for (Allergy a : allergies) {
			System.out.println(a);
		}
	}

public static void showPrescriptions(Patient p) throws IOException, NumberFormatException{
		try {
			System.out.println("You will see your desired PRESCRIPTION. ");
			int patientId=p.getId();
			List<Prescription> prescriptions = prescriptionManager.searchPrescriptionByPatient(patientId);
			if(prescriptions.isEmpty()) {
				System.out.println("No prescriptions found for the PATIENT.");
			}
			else {
				System.out.println("Here are your available PRESCRIPTIONS:");
			for (Prescription presc : prescriptions) {
				System.out.println(presc);
			}}
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid integer ID.");
        }
    }


}

