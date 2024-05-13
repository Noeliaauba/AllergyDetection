package allergyDetection.db.jdbc;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
			String template = "INSERT INTO patient (name, dateOfBirth, gender ) VALUES (?, ?, ?)";
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
public void modifyPatient(Patient p) {
	 try {
	        String query = "UPDATE patient SET name = ?, dateOfBith = ?, gender = ? WHERE id = ?";
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
	String st = "DELETE FROM patient WHERE id = ?";
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

@Override
public Patient getPatientByID(Integer id) {
	try {
		String sql = "SELECT * FROM patient WHERE id = " + id;
		Statement st= c.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		Patient p = new Patient (rs.getInt("id"), rs.getString("name"), rs.getDate("dateOfBith"), rs.getString("gender"));
		return p;
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return null;
}

@Override
public List<Patient> searchPatient(String name_Patient) {
	List<Patient> patients = new ArrayList<Patient>();
	try {
		String sql = "SELECT * FROM patient WHERE name=" + name_Patient;
		PreparedStatement p = c.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			Date dob= rs.getDate("dateOfBirth");
			String gender= rs.getString("gender");
			Patient pat = new Patient(id, name, dob, gender);
			patients.add(pat);
		}
		rs.close();
		p.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return patients;
}

@Override
public void assignedAllergytoPatient(Integer patientId, Integer allergyId) {
	try {
		String template = "INSERT INTO SUFFERS (patient_id, allergy_id) VALUES (?,?)";
		PreparedStatement pstmt = c.prepareStatement(template);
		pstmt.setInt(1, patientId);
		pstmt.setInt(2,allergyId);
		pstmt.executeUpdate();
		pstmt.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	
}

@Override
public void assignedSymptomtoPatient(Integer patientId, Integer symptomId) {
	try {
		String template = "INSERT INTO HAS (patient_id, symptom_id) VALUES (?,?)";
		PreparedStatement pstmt = c.prepareStatement(template);
		pstmt.setInt(1, patientId);
		pstmt.setInt(2,symptomId);
		pstmt.executeUpdate();
		pstmt.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	
}
	
}




