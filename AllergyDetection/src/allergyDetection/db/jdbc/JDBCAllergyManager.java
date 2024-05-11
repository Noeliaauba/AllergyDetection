package allergyDetection.db.jdbc;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.interfaces.AllergyManager;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;

public class JDBCAllergyManager implements AllergyManager {
	
	private Connection c;
	private ConnectionManager conMan;
	
	

	public JDBCAllergyManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void addAllergy(Allergy a) {
		try {
			String template = "INSERT INTO allergy (name,type) VALUES (?,?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, a.getAllergyName());
			pstmt.setString(2,a.getAllergyType());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}

	}

	@Override
	public void modifyAllergy(Allergy a) {
		try {
	        String query = "UPDATE allergy SET name = ?, type = ? WHERE id = ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setString(1, a.getAllergyName());
	        pstmt.setString(2, a.getAllergyType()); 
	        pstmt.setInt(5, a.getAllergyID());
	        pstmt.executeUpdate();
	        pstmt.close();
	        System.out.println("Allergy with ID " + a.getAllergyID() + " updated successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error in the database");
	        e.printStackTrace();
	    }

	}
	@Override
	public Allergy getAllergyByID(Integer id) {
		try {
		String sentence = "SELECT * FROM allergy WHERE id = " + id;
		Statement st;
		st = c.createStatement();
		ResultSet rs = st.executeQuery(sentence);
		rs.next();
		Allergy a = new Allergy (rs.getInt("id"), rs.getString("name"), rs.getString("type"));
		return a;
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return null;
}
	


	 public List<Allergy> searchAllergybyTreatment(Treatment t) {
		
		List<Allergy> allergylist = new ArrayList<Allergy>();
		try {
			String sql = "SELECT * FROM allergies WHERE treatment LIKE ?";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			//p.setString(1, "%" + typeParameter + "%");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id =  rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Allergy newallergy=new Allergy(id,name,type);
				allergylist.add(newallergy);
				//Treatment t = new Treatment(id, name, type); maybe is treatment new_t pq colisionaria con el treatment t q le pasas
				//lista.add(t); 
			}
			rs.close();
			p.close();
			return allergylist;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public List<Allergy> searchAllergy(String type_Allergy) {
		List<Allergy> allergies = new ArrayList<Allergy>();
		try {
			String sql = "SELECT * FROM allergy WHERE type LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Allergy a = new Allergy(id, name, type);
				allergies.add(a);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return allergies;
	}
	
	@Override
	public List<Allergy> searchAllergybyPatient(Integer patientId){
		List<Allergy> allergies = new ArrayList<Allergy>();
		try {
			String sql = "SELECT allergy.id, allergy.name, allergy.type FROM allergy INNER JOIN SUFFERS ON allergy.id=SUFFERS.allergy_id WHERE SUFFERS.patient_id= ?";
			PreparedStatement p= c.prepareStatement(sql);
			p.setInt(1,patientId);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Allergy a = new Allergy(id, name, type);
				allergies.add(a);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return allergies;
		
	}

	@Override
	public void assignedSymptomtoAllergy(Integer symptomId, Integer allergyId) {
		try {
			String template = "INSERT INTO PRODUCES (symptom_id, allergy_id) VALUES (?,?)";
			PreparedStatement pstmt = c.prepareStatement(template);
			pstmt.setInt(1, symptomId);
			pstmt.setInt(2,allergyId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}
	
	public void assignedTreatmenttoAllergy(Integer treatmentId, Integer allergyId) {
		try {
			String template = "INSERT INTO OWNS (treatment_id, allergy_id) VALUES (?,?)";
			PreparedStatement pstmt = c.prepareStatement(template);
			pstmt.setInt(1, treatmentId);
			pstmt.setInt(2,allergyId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}
}


