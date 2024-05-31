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
import allergyDetection.db.interfaces.UserManager;
import allergyDetection.db.jpa.JPAUserManager;
import allergyDetection.db.interfaces.XMLManager;



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
	private static UserManager userMan;
	private static XMLManager xmlManag;
	 
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("WELCOME TO THE ALLERGY DETECTION PROGRAM!");
		System.out.println("");
		
		
		
		conMan = new ConnectionManager();
		patientManag = conMan.getPatient();
		doctorManag = conMan.getDoctor();
		allergyManag= conMan.getAllergy();
		symptomManag= conMan.getSymptom();
		treatmentManag= conMan.getTreatment();
		prescriptionManag= conMan.getPrescription();
		userMan = new JPAUserManager();
		xmlManag = conMan.getXmlManag();
	
	int variableWhileInitial=1;
	while (variableWhileInitial!=0) {	
	System.out.println("Chose your desired option: ");
	System.out.println("1) Log in. ");
	System.out.println("2) Sign up.");
	System.out.println("0) End the program.");

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
		System.out.print("Introduce your username:");
		String username = r.readLine();
		System.out.print("Introduce your password:");
		String password = r.readLine();
		User u = userMan.login(username, password);
		// REMOVE LATER just testing
		if (u == null) 
			System.out.println("The information is incorrect");
		else if (u.getRole().getId()==1) {
			MenuDoctor.menuDoctor(username);
			
		}
		else if(u.getRole().getId()==2) {
			MenuPatient.menuPatient(username);
		}
	}
	
	private static void menuSignUp() throws NumberFormatException, IOException {
		System.out.print("Choose a username:");
		String username = r.readLine();
		System.out.print("Choose a password:");
		String password = r.readLine();
		System.out.println("Are you a patient or a doctor? ");
		String roleName = r.readLine().toLowerCase();
		Role role = userMan.getRole(roleName);
		User u = new User(username, password, role);
		userMan.register(u);
		if(u.getRole().getId()==1) {
			System.out.println("Please, write the information of the doctor:");
			System.out.println("DOCTOR NAME: ");
			String name = r.readLine();
			System.out.println("DOCTOR SURNAME: ");
			String surname = r.readLine();
			Doctor d = new Doctor(name, surname, u.getUsername());
		  doctorManag.addDoctor(d);
		}
		if(u.getRole().getId()==2) {
			System.out.println("Please, write the information of the patient:");
			System.out.println("PATIENT NAME: ");
			String name = r.readLine();
			System.out.println("PATIENT SURNAME: ");
			String surname = r.readLine();
			System.out.println("DATE OF BIRTH (DD-MM-YYYY format): ");
			LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
			Date date = Date.valueOf(localDate);
			System.out.println("PATIENT GENDER: ");
			String gender = r.readLine();
			Patient pat= new Patient(name,surname,date,gender,u.getUsername());
		patientManag.addPatient(pat);
			}
		
	}
	
	
	


	

	
	}
