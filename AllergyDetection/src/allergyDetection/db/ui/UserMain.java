package allergyDetection.db.ui;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.*;
import allergyDetection.db.interfaces.*;
import allergyDetection.db.jdbc.ConnectionManager;
import allergyDetection.db.jdbc.JDBCPatientManager;
import allergyDetection.db.jdbc.*;
import allergyDetection.db.pojos.*;



public class UserMain {

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
		//System.out.println("1) Book a visit: ");
		System.out.println("1) Check your medical score");
		System.out.println("2) Show prescriptions");
		System.out.println("0) This option is always to exit");
	
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
			addPatient();
			
			break;
		
		case 2: 
			deletePatient();
			
			break;
		
		case 3: 
			modifyPatient();
			
			break;
		
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
		}
		
		}
	}
	
	private  Integer id;
	private String name;
	private Date dob;
	private String gender;
	

	private List <Prescription> prescriptions; 
	private List <Allergy> allergies;
	private List<Symptom> symptoms;
	
	private static void addPrescription()  throws NumberFormatException, IOException {
		System.out.println("Please, write the information of the patient and the doctor:");
		System.out.println("Treatment name: ");
		String treatmentName = r.readLine();
		System.out.println("Patient id: ");
		Integer idPatient = Integer.parseInt(r.readLine());
		System.out.println("Doctor id: ");
		Integer idDoctor = Integer.parseInt(r.readLine());
		
		Prescription prescription = new Prescription(_treatment_required, _given_to,_given_by);			
		Prescription.addPrescription(prescription);
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
	
	private static void addPatient()  throws NumberFormatException, IOException {
		System.out.println("Please, write the information of the patient:");
		System.out.println("Patient id: ");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("Patient name: ");
		String name = r.readLine();
		System.out.println("The Date of Birth of the patient(DD-MM-YYYY format): ");
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date date = Date.valueOf(localDate);
		System.out.println("Patient gender: ");
		String gender = r.readLine();

		Patient patient = new Patient(id,name,date,gender);			//we follow the constructor that is in class Patient
		patientManag.addPatient(patient);
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
	
	
	
	private static void deletePatient()  throws NumberFormatException, IOException {
		System.out.println("You will delete a patient from the data base information. ");
		System.out.println("Please, write the id of the patient taht your want to delete: ");
		System.out.println("Patient id: ");
		Integer id = Integer.parseInt(r.readLine());
		patientManag.deletePatient(id);
	}	
		
	private static void modifyPatient() throws NumberFormatException, IOException {
		//TODO delete the comments???
		// Search for an author to be modified
		// User selects the author to be modified
		Patient p=null;
		System.out.println("Here are the actual patient's values");
		System.out.println("Type a new value to modyfy them or just press enter to keep their value.");
		System.out.println("id (" + p.getId() + "): ");
		Integer newId = Integer.parseInt(r.readLine());
		System.out.println("Name (" + p.getName() + "): ");
		String newName = r.readLine();
		System.out.println("The Date of Birth of the patient(DD-MM-YYYY format): "+p.getDob());
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date newDob = Date.valueOf(localDate);
		System.out.println("Gender (" + p.getGender() + "): ");
		String newGender = r.readLine();
		
		if(!newId.equals("")) {
			p.setId(newId);
			}
		if(!newName.equals("")) {
			p.setName(newName);
			}
		if(!newDob.equals("")) {
			p.setDob(newDob);
			}
		if(!newGender.equals("")) {
			p.setGender(newGender);
			}
		patientManag.modifyPatient(p);
		
		//TODO about the prescriptions and allergies and symptoms??? How do we do --> Ask Rodrigo
	}
		
	
		
		
		
		
		
	public static void menuWelcome() {
		System.out.println("Welcome! Select an option: ");
		System.out.println("1) Log in. ");
		System.out.println("2) Sign up.");
		System.out.println("0) End the program.");
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





