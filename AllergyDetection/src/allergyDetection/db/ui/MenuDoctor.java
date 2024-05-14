package allergyDetection.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.interfaces.AllergyManager;
import allergyDetection.db.interfaces.PatientManager;
import allergyDetection.db.interfaces.PrescriptionManager;
import allergyDetection.db.interfaces.SymptomManager;
import allergyDetection.db.interfaces.TreatmentManager;
import allergyDetection.db.*;
import allergyDetection.db.jdbc.*;
import allergyDetection.db.pojos.*;
import allergyDetection.db.jdbc.ConnectionManager;

public class MenuDoctor {
	
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static PatientManager patientManag;
	private static SymptomManager symptomManag;
	private static AllergyManager allergyManag;
	private static TreatmentManager treatmentManag;
	private static PrescriptionManager prescriptionManag;
	private static ConnectionManager conMan;
	
	public static void menuDoctor() throws NumberFormatException, IOException {
		conMan = new ConnectionManager();
		patientManag = conMan.getPatient();
		symptomManag= conMan.getSymptom();
		allergyManag= conMan.getAllergy();
		treatmentManag= conMan.getTreatment();
		prescriptionManag= conMan.getPrescription();
		
		int variableWhileDoctor=1;
		System.out.println("Welcome Doctor! We are delighted with your great job!");
		System.out.println("");
		while(variableWhileDoctor !=0) {
		System.out.println("Select the option desire: ");
		System.out.println("1) ADD A PATIENT TO THE DATA BASE");
		System.out.println("2) DELETE A PATIENT FROM THE DATA BASE");
		System.out.println("3) MODIFY THE INFORMATION OF A PATIENT"); 
		System.out.println("4) ADD SYMPTOMS TO PATIENT");
		System.out.println("5) ADD ALLERGY TO PATIENT");
		System.out.println("6) DIAGNOSE A TREATMENT");	
		System.out.println("7) CREATE A PATIENT'S PRESCRIPTION");
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
			controlSymptom();
			break;
			
		case 5: 
			controlAllergy();
			break;	
			
		case 6: 
			diagnoseTreatment();
			break;
			
		case 7: 
			addPrescription();
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
	
	private static void addPatient()  throws NumberFormatException, IOException {
		System.out.println("Please, write the information of the patient:");
		System.out.println("PATIENT NAME: ");
		String name = r.readLine();
		System.out.println("PATIENT SURNAME: ");
		String surname = r.readLine();
		System.out.println("DATE OF BIRTH OF THE PATIENT(DD-MM-YYYY format): ");
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date date = Date.valueOf(localDate);
		System.out.println("PATIENT GENDER: ");
		String gender = r.readLine();
		Patient patient = new Patient(name,surname,date,gender);		
		patientManag.addPatient(patient);
	}	
	
	private static void deletePatient()  throws NumberFormatException, IOException {
		System.out.println("You will delete a PATIENT from the data base information. ");
		List<Patient> patients = new ArrayList<Patient>();
		System.out.println("Introduce the NAME of the PATIENT you want to select:");
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
		System.out.println("You will MODIFY the information of a patient from the data base:");
		List<Patient> patients = new ArrayList<Patient>();
		System.out.println("Introduce the NAME of the PATIENT you want to select:");
		String Name = r.readLine();
		patients= patientManag.searchPatient(Name);
		for (Patient p : patients) {
			System.out.println(p);
		}
		
	    System.out.println("Introduce the PATIENT ID to modify");
		Integer Id = Integer.parseInt(r.readLine());
		Patient p=patientManag.getPatientByID(Id);
		System.out.println("Here are the actual patients values");
		System.out.println("Press enter to keep them or type a new value.");
		System.out.println("Name (" + p.getName() + "): ");
		String newName = r.readLine();
		if (!newName.equals("")) {
			p.setName(newName);
		}
		System.out.println("Surname(" + p.getSurname() + "): ");
		String newSurname = r.readLine();
		if (!newSurname.equals("")) {
			p.setSurname(newSurname);
		}
		System.out.println("Date Of Birth (" + p.getDob() + "): ");
		String dob=r.readLine();
		if (!dob.equals("")) {
			LocalDate localDate = LocalDate.parse(dob, formatter);
			Date newDate = Date.valueOf(localDate);
			p.setDob(newDate);
		}
		System.out.println("Gender (" + p.getGender() + "): ");
		String newGender = r.readLine();
		if (!newGender.equals("")) {
			p.setGender(newGender);
		}
		patientManag.modifyPatient(p);
		
	}
	
private static void controlSymptom() throws NumberFormatException, IOException{
	System.out.println("You will see the information of a type of symptom pre-saved:");
	List<Symptom> listadosintomas = new ArrayList<Symptom>();
	System.out.println("Introduce the TYPE of SYMPTOM you want to see (Respiratory), (Cutaneous), (Digestive), (Others):");
	String type = r.readLine();
	listadosintomas= symptomManag.searchSymptom(type);
	for (Symptom s : listadosintomas) {
		System.out.println(s);
	}
	System.out.println("Do you want to ADD a new Symptom? YES[y]/NO[n]:");
	String selection = r.readLine();
	while(selection.equals("y")) {
		addSymptom();
		System.out.println("Introduce the TYPE of SYMPTOM you want to see (Respiratory), (Cutaneous), (Digestive), (Others):");
		String type2 = r.readLine();
		listadosintomas= symptomManag.searchSymptom(type2);
		for (Symptom s : listadosintomas) {
			System.out.println(s);
		}
		System.out.println("Do you want to ADD a new Symptom? YES[y]/NO[n]:");
		selection = r.readLine();
		}
	List<Patient> patients = new ArrayList<Patient>();
	System.out.println("Insert NAME the PATIENT you want to add the symptom. ");
	String Name = r.readLine();
	patients= patientManag.searchPatient(Name);
	for (Patient p : patients) {
		System.out.println(p);
	}
	System.out.println("Introduce the PATIENT ID to add the symptom");
	Integer patientId = Integer.parseInt(r.readLine());
	System.out.println("Introduce the SYMPTOM ID to add");
	Integer symptomId = Integer.parseInt(r.readLine());
	patientManag.assignedSymptomtoPatient(patientId, symptomId);
	}
	

private static void addSymptom() throws NumberFormatException, IOException {
	System.out.println("Please, write the symptom information:");
	System.out.println("SYMPTOM NAME: ");
	String name = r.readLine();
	System.out.println("SYMPTOM TYPE: ");
	String type = r.readLine();
	Symptom sm = new Symptom(name,type);		
	symptomManag.addSymptom(sm);
}	

private static void controlAllergy() throws NumberFormatException, IOException{
	System.out.println("You will see the information of a type of allergy pre-saved:");
	List<Allergy> allergies = new ArrayList<Allergy>();
	System.out.println("Introduce the TYPE of ALLERGY you want to see (Alimentary), (Stationary), (Cutaneous), (Drugs), (Insect):");
	String type = r.readLine();
	allergies= allergyManag.searchAllergy(type);
	for (Allergy a : allergies) {
		System.out.println(a);
	}
	System.out.println("Do you want to ADD a new Allergy? YES[y]/NO[n]:");
	String selection = r.readLine();
	while(selection.equals("y")) {
		addAllergy();
		System.out.println("Introduce the TYPE of ALLERGY you want to see (Alimentary), (Stationary), (Cutaneous), (Drugs), (Insect):");
		String type2 = r.readLine();
		allergies= allergyManag.searchAllergy(type2);
		for (Allergy a : allergies) {
			System.out.println(a);
		}
		System.out.println("Do you want to ADD a new Allergy? YES[y]/NO[n]:");
		selection = r.readLine();
		}
	List<Patient> patients = new ArrayList<Patient>();
	System.out.println("Insert NAME the PATIENT you want to add the Allergy. ");
	String Name = r.readLine();
	patients= patientManag.searchPatient(Name);
	for (Patient p : patients) {
		System.out.println(p);
	}
	System.out.println("Introduce the PATIENT ID to add the Allergy detected");
	Integer patientId = Integer.parseInt(r.readLine());
	System.out.println("Introduce the Allergy ID to add");
	Integer allergyId = Integer.parseInt(r.readLine());
	patientManag.assignedAllergytoPatient(patientId, allergyId);
	

	}
	
	

private static void addAllergy() throws NumberFormatException, IOException {
	System.out.println("Please, write the allergy information:");
	System.out.println("ALLERGY NAME: ");
	String name = r.readLine();
	System.out.println("ALLERGY TYPE: ");
	String type = r.readLine();
	Allergy s = new Allergy(name,type);		
	allergyManag.addAllergy(s);
}	

private static void diagnoseTreatment() throws NumberFormatException, IOException {
	List<Patient> patients = new ArrayList<Patient>();
	System.out.println("Introduce the NAME of the PATIENT you want to treat:");
	String Name = r.readLine();
	patients= patientManag.searchPatient(Name);
	for (Patient p : patients) {
		System.out.println(p);
	}
    System.out.println("Introduce the PATIENT ID to treat");
	Integer patientId = Integer.parseInt(r.readLine());
	List<Allergy> allergies = new ArrayList<Allergy>();
	System.out.println("Here are the allergies assigned to the patient");
	allergies=allergyManag.searchAllergybyPatient(patientId);
	for (Allergy a : allergies) {
		System.out.println(a);
	}
	System.out.println("Introduce the ALLERGY ID you select to treat");
	Integer allergyId = Integer.parseInt(r.readLine());
	List<Treatment> treats = new ArrayList<Treatment>();
	treats= treatmentManag.searchTreatmentByType("");
	System.out.println("Here are the available treatments:");
	for (Treatment t : treats) {
		System.out.println(t);
	}
	System.out.println("Introduce the TREATMENT ID you think is appropiate for the allergy ");
	Integer treatmentId = Integer.parseInt(r.readLine());
    allergyManag.assignedTreatmenttoAllergy(treatmentId, allergyId);
}



private static void addPrescription() throws NumberFormatException, IOException {
	System.out.println("Please, select the PATIENT to elaborate the prescription:");
	List<Patient> patients = new ArrayList<Patient>();
	patients= patientManag.searchPatient("");
	for (Patient p : patients) {
		System.out.println(p);
	}
	System.out.println("PATIENT ID: ");
	Integer patId = Integer.parseInt(r.readLine());
	List<Allergy> allergies = new ArrayList<Allergy>();
	allergies= allergyManag.searchAllergybyPatient(patId);
	for (Allergy a : allergies) {
		System.out.println(a);
	}
	System.out.println("Select the ALLERGY to treat:");
	System.out.println("ALLERGY ID:");
	Integer allergyId = Integer.parseInt(r.readLine());
	List<Treatment> treatments = new ArrayList<Treatment>();
	treatments=treatmentManag.searchTreatmentByAllergy(allergyId);
	for (Treatment t : treatments) {
		System.out.println(t);
	}
	System.out.println("Select the TREATMENT to ADD in the prescription:");
	System.out.println("TREATMENT ID:");
	Integer treatmentId = Integer.parseInt(r.readLine());
	Treatment tratamiento= treatmentManag.getTreatmentById(treatmentId);
	Patient patient= patientManag.getPatientByID(patId);
	Doctor doctor= new Doctor(4, "Sonia", "Ramos");
	Prescription ps = new Prescription("NO",patient,doctor,tratamiento);		
	prescriptionManag.addPrescription(ps);
}





}


