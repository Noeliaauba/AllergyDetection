package allergyDetection.db.implementations;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.interfaces.*;

public class PatientImplementation implements PatientManager {
	
@Override
	public void addPatient(Patient p) {
		try {
			String template = "INSERT INTO patient (name,dob,gender ) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getSurname());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}

}

@Override
public void modifyPatient(Integer id) {
	// TODO Auto-generated method stub
	
}

@Override
public void deletePatient(Integer id) {
	// TODO Auto-generated method stub
	
}

@Override
public Patient searchPatient(Integer id) {
	// TODO Auto-generated method stub
	return null;
}
/*import library.db.interfaces.AuthorManager;
import library.db.pojos.Author;
import library.db.pojos.Book;

public class JDBCAuthorManager implements AuthorManager {

	private Connection c;
	private ConnectionManager conMan;

	public JDBCAuthorManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	

*/