package allergyDetection.db.jdbc;
import allergyDetection.db.pojos.Patient;

import java.sql.*;

import allergyDetection.db.interfaces.*;

public class JDBCPatientManager implements PatientManager {
	
	private Connection c;
	private ConnectionManager conMan;

	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
@Override
	public void addPatient(Patient p) {
		try {
			String template = "INSERT INTO patients (name,dob,gender ) VALUES (?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, p.getName());
			pstmt.setDate(2, p.getDob());
			pstmt.setString(3, p.getGender());
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}

}


@Override
public Patient getPatientByID(Integer id) {
	try {
		String sql = "SELECT * FROM patients WHERE id = " + id;
		Statement st;
		st = c.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		Patient p = new Patient (rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("gender"));
		return p;
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return null;
}

@Override
public void modifyPatient(Patient p) {
	 try {
	        String query = "UPDATE patients SET name = ?, dob = ?, gender = ? WHERE id = ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setString(1, p.getName());
	        pstmt.setDate(2, p.getDob()); 
	        pstmt.setString(3, p.getGender());
	        pstmt.setInt(4, p.getId());
	        pstmt.executeUpdate();
	        pstmt.close();
	        System.out.println("Patient with ID " + p.getId() + " updated successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error in the database");
	        e.printStackTrace();
	    }
}
	

@Override
public void deletePatient(Integer id) {
	try {
	String st = "DELETE FROM patients WHERE id = ?";
	PreparedStatement pstmt = c.prepareStatement(st);
    pstmt.setInt(1, id);
        int rowsAffected = pstmt.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Deleted successfully the patient with ID"+ id);
    } else {
        System.out.println("Patient not found with ID " + id);
    }
    pstmt.close();
} catch (SQLException e) {
    System.out.println("Error in data bases with the patient ID " + id);
    e.printStackTrace();
}	
}



}
