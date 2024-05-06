package allergyDetection.db.jdbc;

import allergyDetection.db.interfaces.PrescriptionManager;
import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Treatment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class JDBCPrescriptionManager implements PrescriptionManager {
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCPrescriptionManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void addPrescription(Prescription k) {
		try {
			String template = "INSERT INTO prescription (treatment_required, isUsed, given_to, given_by) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, k.getTreatment_required().getId());		//because Treatment has an Id that is a number
			pstmt.setBoolean(2, k.getIsUsed());
			pstmt.setInt(3, k.getGiven_To().getId());		//because Patient has an Id that is a number
			pstmt.setInt(4, k.getGiven_by().getId());		//because Doctor has an Id that is a number
	
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}		
	}


	@Override   
	public void modifyPrescription(Prescription k) {
		try {
	        String query = "UPDATE prescription  SET treatment_required = ?, isUsed = ?,given_to = ?,given_by = ?,  WHERE id = ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setInt(1, k.getTreatment_required().getId());		//because Treatment has an Id that is a number
			pstmt.setBoolean(2, k.getIsUsed());
			pstmt.setInt(3, k.getGiven_To().getId());		//because Patient has an Id that is a number
			pstmt.setInt(4, k.getGiven_by().getId());		//because Doctor has an Id that is a number
			pstmt.setInt(5, k.getId());

	        pstmt.executeUpdate();
	        pstmt.close();
	        System.out.println("Prescription with ID: " + k.getId() + " updated successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error in the database");
	        e.printStackTrace();
	    }
	}

	@Override
	public void deletePrescription(Prescription k) {	//TODO verificate if here is prescription k or integer id

		try {
			String query = "DELETE FROM prescription WHERE id = ?";
			PreparedStatement pstmt = c.prepareStatement(query);
		    pstmt.setInt(1, k.getId());
		    int rowsAffected = pstmt.executeUpdate();
		    if (rowsAffected > 0) {
		        System.out.println("Deleted successfully the prescription with ID"+ k.getId());
		    } else {
		        System.out.println("Prescription not found with ID " + k.getId());
		    }
		    pstmt.close();
		} catch (SQLException e) {
		    System.out.println("Error in data bases with the prescription ID " + k.getId());
		    e.printStackTrace();
		}	
		
		
	}


	
	@Override
	public Prescription showPrescription(Integer id) {
		Prescription prescription = null;
	    try {
	        String query = "SELECT * FROM prescription WHERE id=?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setInt(1, id);		//not k.getId() because the method receives it directly
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            int prescriptionId = rs.getInt("id");
	            //Now everything of the prescription object
	            Treatment treatment = new Treatment(); 
	            treatment.setId(rs.getInt("treatment_id")); 
	            Boolean isUsed = rs.getBoolean("isUsed");
	            Patient patient = new Patient(); 
	            patient.setId(rs.getInt("patient_id")); 
	            Doctor doctor = new Doctor(); 
	            doctor.setId(rs.getInt("doctor_id")); 
	            
	            prescription = new Prescription(prescriptionId, treatment, isUsed, patient, doctor);
	        }
	        rs.close();
	        pstmt.close();
	    } catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	    return prescription;
	}
	
	@Override
	public Prescription getPrescriptionById(Integer id) {
		try {
			String sql = "SELECT * FROM patient WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Prescription pr = new Prescription (rs.getInt("id"), null, null, null, null);
			return pr;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

}




