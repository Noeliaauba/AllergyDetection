package allergyDetection.db.ui;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import allergyDetection.db.interfaces.AllergyManager;
import allergyDetection.db.interfaces.DoctorManager;
import allergyDetection.db.interfaces.PatientManager;
import allergyDetection.db.interfaces.PrescriptionManager;
import allergyDetection.db.interfaces.SymptomManager;
import allergyDetection.db.interfaces.TreatmentManager;
import allergyDetection.db.interfaces.XMLManager;
import allergyDetection.db.*;
import allergyDetection.db.jdbc.*;
import allergyDetection.db.pojos.*;
import allergyDetection.db.jdbc.ConnectionManager;

public class MenuDoctor {
	
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static PatientManager patientManag;
	private static DoctorManager doctorManag;
	private static SymptomManager symptomManag;
	private static AllergyManager allergyManag;
	private static TreatmentManager  treatmentManag;
	private static PrescriptionManager prescriptionManag;
	private static XMLManager xmlManag;
	private static ConnectionManager conMan;
	
	public static void menuDoctor(String username) throws NumberFormatException, IOException {
		conMan = new ConnectionManager();
		patientManag = conMan.getPatient();
		doctorManag = conMan.getDoctor();
		symptomManag= conMan.getSymptom();
		allergyManag= conMan.getAllergy();
		treatmentManag= conMan.getTreatment();
		prescriptionManag= conMan.getPrescription();
		xmlManag = conMan.getXmlManag();
		
		int variableWhileDoctor=1;
		System.out.println("Welcome Doctor! We are delighted with your great job!");
		System.out.println("");
		Doctor d= doctorManag.getDoctorByusername(username);
		while(variableWhileDoctor !=0) {
		System.out.println("Select the option desire: ");
		System.out.println("1) ADD A PATIENT TO THE DATA BASE");
		System.out.println("2) DELETE A PATIENT FROM THE DATA BASE");
		System.out.println("3) MODIFY THE INFORMATION OF A PATIENT"); 
		System.out.println("4) ADD SYMPTOMS TO PATIENT");
		System.out.println("5) ADD ALLERGY TO PATIENT");
		System.out.println("6) DIAGNOSE A TREATMENT");	
		System.out.println("7) CREATE A PATIENT'S PRESCRIPTION");
		System.out.println("8) UPDATE MEDICAL SCORE");
		System.out.println("9) GENERATE XML ");
		System.out.println("10) DOWNLOAD XML");
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
			addPrescription(d);
			break;
			
		case 8: 
			medicalScore();
			break;
			
		case 9: 
			System.out.println("Do you want to do it over a PATIENT [p] or DOCTOR[d]:");
			String selection = r.readLine();
			if(selection.equals("p")) {
				System.out.println("Please, select the PATIENT to modify the medical score:");
		    	List<Patient> pat = new ArrayList<Patient>();
		    	pat= patientManag.searchPatient("");
		    	for (Patient p : pat) {
		    		System.out.println(p);
		    	}
		    	System.out.println("Introduce the PATIENT ID:");
				Integer p_id = Integer.parseInt(r.readLine());
				Patient p=patientManag.getPatientByID(p_id);
				savePatientToFile(p);
				}
			if(selection.equals("d")) {
				System.out.println("Your are going to generate your information to xml:");
				saveDoctorToFile(d);
			}
			break;
			
		case 10: 
			List<String> files = getXMLFilenamesInFolder();
			downloadXML(files);
			System.out.println("Object Added");
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


    private static void addPrescription(Doctor doctor) throws NumberFormatException, IOException {
	System.out.println("Please, select the PATIENT to elaborate the prescription:");
	List<Patient> pat = new ArrayList<Patient>();
	pat= patientManag.searchPatient("");
	for (Patient p : pat) {
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
	Prescription ps = new Prescription("NO",patient,doctor,tratamiento);		
	prescriptionManag.addPrescription(ps);
}

    private static void medicalScore() throws NumberFormatException, IOException {
    	System.out.println("Please, select the PATIENT to modify the medical score:");
    	List<Patient> pat = new ArrayList<Patient>();
    	pat= patientManag.searchPatient("");
    	for (Patient p : pat) {
    		System.out.println(p);
    	}
    	System.out.println("PATIENT ID: ");
    	Integer patId = Integer.parseInt(r.readLine());
    	List<Allergy> allergies = new ArrayList<Allergy>();
    	List<Symptom> syms = new ArrayList<Symptom>();
    	System.out.println("Do you want to DELETE a SYMPTOM? YES[y]/NO[n]:");
    	String selection2 = r.readLine();
    	while(selection2.equals("y")) {
    		syms= symptomManag.searchSymptombyPatient(patId);
        	for (Symptom s : syms) {
        		System.out.println(s);
        	}
        	System.out.println("Select the SYMPTOM to delete:");
        	System.out.println("SYMPTOM ID:");
        	Integer symptomId = Integer.parseInt(r.readLine());
        	symptomManag.deleteSymptom(symptomId);
    		System.out.println("Do you want to DELETE another SYMPTOM? YES[y]/NO[n]:");
    		selection2 = r.readLine();
    	}
    	System.out.println("Do you want to DELETE an ALLERGY? YES[y]/NO[n]:");
    	String selection = r.readLine();
    	while(selection.equals("y")) {
    		allergies= allergyManag.searchAllergybyPatient(patId);
        	for (Allergy a : allergies) {
        		System.out.println(a);
        	}
        	System.out.println("Select the ALLERGY to delete:");
        	System.out.println("ALLERGY ID:");
        	Integer allergyId = Integer.parseInt(r.readLine());
        	allergyManag.deleteAllergy(allergyId);
    		System.out.println("Do you want to DELETE another ALLERGY? YES[y]/NO[n]:");
    		selection = r.readLine();
    	}
        
    }
    private static void uploadPatientXML(Patient p) throws NumberFormatException, IOException {
    	System.out.println(p.toString());
    	xmlManag.patient2XML(p);
}
    
    private static void uploadPatientHTML(Patient p) throws NumberFormatException, IOException {
    	System.out.println(p.toString());
    	xmlManag.patient2Html(p);
   
}

private static void savePatientToFile(Patient p ) {
System.out.println(" What do you want to generate for a PATIENT:"
		+ "\n   1. XML file"
		+ "\n   2. HTML file");
Integer option=0;
try {
	option = Integer.parseInt(r.readLine());
	switch(option) {
	case 1:
		System.out.println("Save to XML file:");
		uploadPatientXML(p);
		break;
	case 2:
		System.out.println("Save to HTML file:");
		uploadPatientHTML(p);
		break;
	default:
		System.out.println(" ERROR: invalid option.");
}
	
} catch (NumberFormatException e) {
	e.printStackTrace();
} catch (IOException e) {
	e.printStackTrace();
}
}


    
    
    
private static void uploadDoctorXML(Doctor d) throws NumberFormatException, IOException {
	System.out.println(d.toString());
	xmlManag.doctor2XML(d);
}
    
private static void uploadDoctorHTML(Doctor d) throws NumberFormatException, IOException {
	System.out.println(d.toString());
	xmlManag.doctor2Html(d);
}
     
private static void saveDoctorToFile(Doctor d ) {
System.out.println(" What do you want to generate for a DOCTOR:"
		+ "\n   1. XML file"
		+ "\n   2. HTML file");
Integer option=0;
try {
	option = Integer.parseInt(r.readLine());
	switch(option) {
	case 1:
		System.out.println("Save to XML file:");
		uploadDoctorXML(d);
		break;
	case 2:
		System.out.println("Save to HTML file:");
		uploadDoctorHTML(d);
		break;
	default:
		System.out.println(" ERROR: invalid option.");
}
	
} catch (NumberFormatException e) {
	e.printStackTrace();
} catch (IOException e) {
	e.printStackTrace();
}
}

private static List<String> getXMLFilenamesInFolder() {
	List<String> xmlFile = new ArrayList<>();
	File folder = new File("./xmls");
	if (folder.isDirectory()) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
					xmlFile.add(file.getName());
				}
			}
		}
	}
	return xmlFile;
}

    private static void downloadXML(List<String> xmlFile) throws NumberFormatException, IOException {
    	int cont = 1;
    	System.out.println(" -Which file do you want to load: ");
    	Iterator<String> it = xmlFile.iterator();
    	while(it.hasNext()) {
    		System.out.println("   " + cont + ". " + it.next());
    		cont++;
    	}
    	Integer option=0;
    		try {
    	 	do {
    	 		System.out.println(" Choose a file given: ");
        		option = Integer.parseInt(r.readLine())-1;
        		if(option < 0) {
        			System.out.println(" ERROR: Invalid option.");
        		}
        	} while(option < 0 );	
    		File fileName = new File("./xmls/" + xmlFile.get(option));
    		
    	   	if(xmlFile.get(option).endsWith("-Patient.xml") ){
        		Patient p = xmlManag.XML2patient(fileName);
        		patientManag.addPatient(p);
        		}
        	if(xmlFile.get(option).endsWith("-Doctor.xml")) {
        		Doctor d = xmlManag.XML2doctor(fileName);
        		doctorManag.addDoctor(d);
    		}    

    }catch (NumberFormatException e) {
    	e.printStackTrace();
    } catch (IOException e) {
    	e.printStackTrace();
    }
    }
    

    
}


