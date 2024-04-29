package allergyDetection.db.ui;
//import la clase
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

public class UserMain {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int choice = Integer.parseInt(r.readLine());
		switch (choice) {
		case 1: {
			//menuLogin();
			break;
		}
		case 2: {
			//menuSignUp();
			break;
		}
		case 0: {
			return;
		}
		}
	
}
