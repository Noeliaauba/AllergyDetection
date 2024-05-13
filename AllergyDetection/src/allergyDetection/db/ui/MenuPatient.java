package allergyDetection.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import allergyDetection.db.jdbc.JDBCPrescriptionManager;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;

public class MenuPatient {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	
	
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
			//conMan.close();	
			break;
		
		default:
			System.out.println("You inserted a number not accepted. Please, select again a number of the following options");
		}
		
		}

	}
	
	//Patients methods.

/*public static void showPrescription() throws IOException, NumberFormatException{
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



}
