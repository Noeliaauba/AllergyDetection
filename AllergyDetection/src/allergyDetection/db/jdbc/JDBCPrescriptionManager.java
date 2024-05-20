package allergyDetection.db.jdbc;
import allergyDetection.db.interfaces.PatientManager;
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
			String template = "INSERT INTO prescription (isUsed, given_to, given_by, treatment_req) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt= c.prepareStatement(template);
			pstmt.setString(1, k.getIsUsed());
			pstmt.setInt(2, k.getGiven_To().getId());		
			pstmt.setInt(3, k.getGiven_by().getId());
			pstmt.setInt(4, k.getTreatment().getId());
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
	        String query = "UPDATE prescription  SET  isUsed = ?, given_to = ?, given_by = ?, treatment_req = ?  WHERE id = ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
			pstmt.setString(1, k.getIsUsed());
			pstmt.setInt(2, k.getGiven_To().getId());		
			pstmt.setInt(3, k.getGiven_by().getId());	
			pstmt.setInt(4, k.getTreatment().getId());
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
	public void deletePrescription(Integer id) {	
		try {
			String query = "DELETE FROM prescription WHERE id = ?";
			PreparedStatement pstmt = c.prepareStatement(query);
		    pstmt.setInt(1, id);
		    int rowsAffected = pstmt.executeUpdate();
		    if (rowsAffected > 0) {
		        System.out.println("Deleted successfully the prescription with ID"+ id);
		    } else {
		        System.out.println("Prescription not found with ID "+ id);
		    }
		    pstmt.close();
		} catch (SQLException e) {
		    System.out.println("Error in data bases with the prescription ID " + id);
		    e.printStackTrace();
		}			
		
	}

	@Override
	public Prescription getPrescriptionById(Integer id) {
		try {
			String sql = "SELECT * FROM prescription WHERE id = " + id;
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			int patientid = rs.getInt("given_to");
			int doctorid= rs.getInt("given_by");
			int treatmentid= rs.getInt("treatment_req");
			Patient p = conMan.getPatient().getPatientByID(patientid);
			Doctor d= conMan.getDoctor().getDoctorByID(doctorid);
			Treatment t= conMan.getTreatment().getTreatmentById(treatmentid);
			Prescription pr = new Prescription (rs.getInt("id"), rs.getString("isUsed"), p, d, t);
			return pr;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List <Prescription> searchPrescriptionByPatient(Integer patientid) {
		List<Prescription> prescriptions = new ArrayList<Prescription>();
	    try {
	        String query = "SELECT prescription.id, prescription.isUsed ,prescription.given_to, prescription.given_by, prescription.treatment_req FROM prescription INNER JOIN patient ON prescription.given_to=patient.id WHERE patient.id= ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setInt(1, patientid);	
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	Integer prescriptionId = rs.getInt("id");
	            String isUsed = rs.getString("isUsed");
	    		int patient_id = rs.getInt("given_to");
				int doctorid= rs.getInt("given_by");
				int treatmentid= rs.getInt("treatment_req");
	            Patient p = conMan.getPatient().getPatientByID(patient_id);
				Doctor d= conMan.getDoctor().getDoctorByID(doctorid);
				Treatment t= conMan.getTreatment().getTreatmentById(treatmentid);
	            Prescription pre = new Prescription(prescriptionId, isUsed, p, d, t);
				prescriptions.add(pre);
	        }
	        rs.close();
	        pstmt.close();
	    } catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	    return prescriptions;
	}
	
	@Override
	public List <Prescription> searchPrescriptionByDoctor(Integer doctorid) {
		List<Prescription> prescriptions = new ArrayList<Prescription>();
	    try {
	        String query = "SELECT prescription.id, prescription.isUsed,prescription.given_to, prescription.given_by, prescription.treatment_req FROM prescription INNER JOIN doctor ON prescription.given_to=doctor.id WHERE doctor.id= ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setInt(1, doctorid);	
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	Integer prescriptionId = rs.getInt("id");
	            String isUsed = rs.getString("isUsed");
	            Patient patient = new Patient(); 
	            patient.setId(rs.getInt("patient_id")); 
	            Doctor doctor = new Doctor(); 
	            doctor.setId(rs.getInt("doctor_id")); 
	            Treatment treatment= new Treatment ();
	            treatment.setId(rs.getInt("treatment_req"));
	            Prescription p = new Prescription(prescriptionId, isUsed, patient, doctor, treatment);
				prescriptions.add(p);
	        }
	        rs.close();
	        pstmt.close();
	    } catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	    return prescriptions;
	}
}




