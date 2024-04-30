package allergyDetection.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.List;

import library.db.jdbc.ConnectionManager;
import library.db.jpa.JPAUserManager;
import library.db.pojos.Role;
import library.db.pojos.User;


public class UserMain {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("Welcome to the library!");
		// Manager setup
		// ALWAYS JDBC first
	
		// JAP later
		Menu.menuWelcome();
	
		int choice = Integer.parseInt(r.readLine());
		
		switch (choice) {
		case 1: {
			menuLogin();
			break;
		}
		case 2: {
			menuSignUp();
			break;
		}
		case 0: {
			conMan.close();
			return;
		}
		default:
			System.out.println("Insert an integer that correspond to to one of the following options");
		}
	}
	
	
	//falta aplicarlo
	/*
	while (true) {
        System.out.println("Enter your choice:");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Exit");

        choice = Integer.parseInt(r.readLine());

        switch (choice) {
            case 1:
                menuLogin();
                break;
            case 2:
                menuSignUp();
                break;
            case 0:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Insert an integer that corresponds to one of the following options");
        }
	
	
	*/
	private static void menuLogin() throws NumberFormatException, IOException {
		//TODO method 
	}
	
	private static void menuSignUp() throws NumberFormatException, IOException {
		//TODO method
	}
	
	
	
	

	public static void  menuPatient() {
		System.out.println("Welcome patient! Select the number of the follow option that you want to do: ");
		System.out.println("1) Book a visit: ");
		System.out.println("2) Look for the visit that are booked");
		System.out.println("3) Check your medical score");
		System.out.println("4) Show prescriptions");
		System.out.println("0) This option is always to exit");
	
	}
	
	public static void menuDoctor() {
		System.out.println("Welcome Doctor! We are delighted with your great job!");
		System.out.println("Select the number of the follow option that you want to do: ");
		System.out.println("1) Add a patient to the data base");
		System.out.println("2) Delete a patient from the data base");
		System.out.println("3) Modify the information of a patient. Remember that the patient must be registered first.");
		System.out.println("4) See the patient medical score");		//it was said "patient records" are we looking for the same?
		System.out.println("5) Add the symptoms of a patient");
		System.out.println("6) Write a prescription for a patient. Remember that the patient must be registered first.");
		System.out.println("7) Modify the prescription of a patient.Remember that the patient and the prescription must be created first.");
		System.out.println("0) Select this option to exit.");
	
	}
	
	public static void menuWelcome() {
		System.out.println("Welcome! Select the an option: ");
		System.out.println("1) Log in. ");
		System.out.println("2) Sign up.");
		System.out.println("0) End the program.");
	}
	
	
	
	
	
}

