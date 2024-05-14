package allergyDetection.db.ui;

import java.io.BufferedReader;

import allergyDetection.db.jdbc.ConnectionManager;
import allergyDetection.db.interfaces.*;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;

public class MenuPatient {
	
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

	private static PrescriptionManager prescriptionManager;
	private static PatientManager patientManager;
	private static ConnectionManager conMan;
	
	
	public static void  menuPatient() throws NumberFormatException, IOException{
		conMan = new ConnectionManager();
		
		
		prescriptionManager =conMan.getPrescription();
		patientManager = conMan.getPatient();
		
		System.out.println("Welcome patient! Select the option: ");
		int variableWhilePatient=1;
		while(variableWhilePatient!=0) {
		System.out.println("1) CHECK YOUR MEDICAL SCORE");
		System.out.println("2) SHOW PRESCRIPTION");
		System.out.println("0) EXIT");
		int choicePatient = Integer.parseInt(r.readLine());
		switch (choicePatient) {
		
		case 1: 
			checkMedicalScore(); 
			break;
		
		case 2: 
			showPrescription();
			
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
	
	
	public static void checkMedicalScore()throws IOException, NumberFormatException {
		
		Patient patient =new Patient();
		System.out.println("Insert patient id:");
		int id =Integer.parseInt(r.readLine());
		System.out.print("Patient with id:" + patient.getId());
		System.out.println("Medical Score:"+ patient.toString());
		
		
	}

public static void showPrescription() throws IOException, NumberFormatException{
	
		try {
			System.out.println("You will see a prescription. ");
			
			System.out.println("Insert patient id: ");
			int patientId=Integer.parseInt(r.readLine());
			
			Patient patient = new Patient();
			patient= patientManager.getPatientByID(patientId);
			
			List<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions=prescriptionManager.searchPrescriptionByPatient(patientId);
			
			System.out.println("Insert prescription id:");
			int prescriptionId=Integer.parseInt(r.readLine());
			Prescription prescription =new Prescription();
			prescription=prescriptionManager.getPrescriptionById(prescriptionId);

            if (prescriptions.equals(patient)) {
            	 System.out.println("Prescriptions for patient with ID :" + patient.getId());
            	 
                 for (Prescription p : prescriptions) {
                     System.out.println(prescription);
                 }
                
            } else {
            	System.out.println("No prescriptions found for the given ID.");
               
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid integer ID.");
        }
    }



}
