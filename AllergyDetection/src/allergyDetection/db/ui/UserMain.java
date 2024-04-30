package allergyDetection.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.*;
import allergyDetection.db.interfaces.*;
import allergyDetection.db.pojos.*;

public class UserMain {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("Welcome to the library!");
		// Manager setup
		// ALWAYS JDBC first
	
		// JAP later
		int variableWhileInitial=1;
		while (variableWhileInitial!=0) {
	        menuWelcome();
	        int choice = Integer.parseInt(r.readLine());

	        switch (choice) {
	            case 1:
	                menuLogin();
	                break;
	            case 2:
	                menuSignUp();
	                break;
	            case 0:
	                System.out.println("Exiting...");
	                //TODO closing the program
	                //variableWhileInitial=0;
	                return;
	            default:
	                System.out.println("Insert an integer that corresponds to one of the following options. Use 0 For exit the program");
	        }
		}
	}
		
		
	private static void menuLogin() throws NumberFormatException, IOException {
		//TODO method 
	}
	
	private static void menuSignUp() throws NumberFormatException, IOException {
		//TODO method
	}
	
	
	
	

	public static void  menuPatient() throws NumberFormatException, IOException{
		System.out.println("Welcome patient! Select the number of the follow option that you want to do: ");
		
		
		int variableWhilePatient=1;
		
		while(variableWhilePatient!=0) {
		System.out.println("1) Book a visit: ");
		System.out.println("2) Look for the visit that are booked");
		System.out.println("3) Check your medical score");
		System.out.println("4) Show prescriptions");
		System.out.println("0) This option is always to exit");
	
		int choicePatient = Integer.parseInt(r.readLine());
		switch (choicePatient) {
		case 1: 
			//selectVisitDate();
			//TODO the method. This method can be done here in this class
			
			break;
		
		case 2: 
			//LookForTheListOfVisitBooked();
			//TODO the method. This method can be done here 
			break;
		
		case 3: 
			//checkMedicalScore();
			//TODO the method. This method can be done here 

			break;
		
		case 4: 
			//showPrescription();
			System.out.println("You want to see the prescription");
			//TODO the method. This method can be done here 

			break;
		
		
		case 0:
			// TODO CLOSE EVRYTHING
			variableWhilePatient=0;
			
		break;
		
		default:
			System.out.println("You inserted a number not accepted. Please, select again a number of the following options");
		}
		
		}

	}
	
	
/*public static void showPrescription() throws IOException, NumberFormatException{
		try {
			getPrescriptionsByPatientId();
            // Fetch prescriptions by patient ID
            List<Prescription> prescriptions = getPrescriptionsByPatientId(); //A method 

            if (prescriptions.isEmpty()) {
                System.out.println("No prescriptions found for the given ID.");
            } else {
                System.out.println("Prescriptions for patient with ID " + patientId + ":");
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
		
	
	private static void searchBooksByTitle() throws IOException {
		System.out.println("Please, type the book title");
		String title = r.readLine();
		List<Book> books = bookMan.searchBookByTitle(title);
		for (Book book : books) {
			System.out.println(book);
		}
	}

		
	
public static void getPrescriptionsByPatientId() {
	System.out.println("Please, type your ID as a patient");
	int id = Integer.parseInt(r.readLine());
  List<Prescription> prescriptions = PrescriptionManager.SelectPrescriptionById(id);
  for(Prescription prescrip : prescriptions) {
	  System.out.println(prescrip);
  }
}
*/

  
  	

	public static void menuDoctor() throws NumberFormatException, IOException {
		System.out.println("Welcome Doctor! We are delighted with your great job!");

		int variableWhileDoctor=1;
		
		while(variableWhileDoctor !=0) {
		System.out.println("Select the number of the follow option that you want to do: ");
		System.out.println("1) Add a patient to the data base");
		System.out.println("2) Delete a patient from the data base");
		System.out.println("3) Modify the information of a patient. Remember that the patient must be registered first.");
		System.out.println("4) See the patient medical score");		//it was said "patient records" are we looking for the same?
		System.out.println("5) Add the symptoms of a patient");
		System.out.println("6) Write a prescription for a patient. Remember that the patient must be registered first.");
		System.out.println("7) Modify the prescription of a patient.Remember that the patient and the prescription must be created first.");
		System.out.println("0) Select this option to exit.");
	
		

		int choiceDoctor = Integer.parseInt(r.readLine());
		switch (choiceDoctor) {
		case 1: 
			//addPatient();
			//TODO the method. This method can be done here in this class
			
			break;
		
		case 2: 
			//deletePatient();
			//TODO the method. This method can be done here 
			break;
		
		case 3: 
			//modifyInformationOfPatient();
			//TODO the method. This method can be done here 

			break;
		
		case 4: 
			//showMedicalScore();
			//TODO the method. This method can be done here 

			break;
			
		case 5: 
			//addSymptoms();
			//TODO the method. This method can be done here 

			break;	
		case 6: 
			//showMedicalScore();
			//TODO the method. This method can be done here 

			break;
			
		case 7: 
			//showMedicalScore();
			//TODO the method. This method can be done here 

			break;
		
		case 0:
			// TODO CLOSE EVRYTHING
			variableWhileDoctor=0;
			
		break;
		
		default:
			System.out.println("You inserted a number not accepted. Please, select again a number of the following options");
		}
		
		}
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	public static void menuWelcome() {
		System.out.println("Welcome! Select an option: ");
		System.out.println("1) Log in. ");
		System.out.println("2) Sign up.");
		System.out.println("0) End the program.");
	}
	
	
	
	
	
}

