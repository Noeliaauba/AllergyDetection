package allergyDetection.db.ui;

public class Menu {

	public void  menuPatient() {
		System.out.println("Welcome patient! Select the number of the follow option that you want to do: ");
		System.out.println("1) Book a visit: ");
		System.out.println("2) Look for the visit that are booked");
		System.out.println("3) Check your medical score");
		System.out.println("4) Show prescriptions");
		System.out.println("0) This option is always to exit");
	
	}
	
	public void menuDoctor() {
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
	
	public void menuWelcome() {
		System.out.println("Welcome! Select the an option: ");
		System.out.println("1) Log in. ");
		System.out.println("2) Sign up.");
		System.out.println("0) End the program.");
	}
	
}
