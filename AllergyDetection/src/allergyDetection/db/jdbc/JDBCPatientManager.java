package allergyDetection.db.jdbc;
import allergyDetection.db.pojos.Patient;
import library.db.pojos.Author;

import java.sql.*;

import allergyDetection.db.interfaces.*;

public class JDBCPatientManager implements PatientManager {
	
}
	private Connection c;
	private ConnectionManager conMan;

	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
@Override
	public void addPatient(Patient p) {
		try {
			String template = "INSERT INTO patient (name,dob,gender ) VALUES (?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, p.getName());
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
/*@Override
	public void changeAuthor(Author a) {
		// TODO Complete the method with this query
		String template = "UPDATE authors SET name = ?, surname = ? WHERE id = ?";

	}

	@Override
	public Author getAuthor(int id) {
		try {
			String sql = "SELECT * FROM authors WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Author a = new Author (rs.getInt("id"), rs.getString("name"), rs.getString("surname"));
			return a;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

	

*/