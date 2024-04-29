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
	
	
	
	
	
	
	
	
}

