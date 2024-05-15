package allergyDetection.db.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import allergyDetection.db.interfaces.SymptomManager;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;

public class JDBCSymptomManager implements SymptomManager {
	
	private Connection c;
	private ConnectionManager conMan;
	

	public JDBCSymptomManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void addSymptom(Symptom s) {
		try {
			String template = "INSERT INTO symptom (name,type) VALUES (?,?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, s.getSymptom_name());
			pstmt.setString(2, s.getSymptomType());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}

	}

	@Override
	public void modifySymptom(Symptom s) {
		try {
	        String query = "UPDATE symptom SET name = ?, type = ?  WHERE id = ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setString(1, s.getSymptom_name());
	        pstmt.setString(2, s.getSymptomType()); 
	        pstmt.setInt(3, s.getId());
	        pstmt.executeUpdate();
	        pstmt.close();
	        System.out.println("Symptom with ID " + s.getId() + " updated successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error in the database");
	        e.printStackTrace();
	    }

	}
	
	@Override
	public Symptom getSymptomByID(Integer id) {
		try {
			String sql = "SELECT * FROM symptom WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Symptom s = new Symptom (rs.getInt("id"), rs.getString("name"), rs.getString("type"));
			return s;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Symptom> searchSymptom(String type_Symptom){
		List<Symptom> symptoms = new ArrayList<Symptom>();
		try {
			String sql = "SELECT * FROM symptom WHERE type LIKE ?";
			PreparedStatement p= c.prepareStatement(sql);
			p.setString(1,type_Symptom);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Symptom s = new Symptom(id, name, type);
				symptoms.add(s);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return symptoms;
		
	}
	
	public List<Symptom> searchSymptombyPatient(Integer patientID){
		List<Symptom> syms = new ArrayList<Symptom>();
		try {
			String sql = "SELECT symptom.id, symptom.name, symptom.type FROM symptom INNER JOIN HAS ON symptom.id=HAS.symptom_id WHERE SUFFERS.patient_id= ?";
			PreparedStatement p= c.prepareStatement(sql);
			p.setInt(1,patientID);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Symptom s = new Symptom(id, name, type);
				syms.add(s);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return syms;
		
		
		
	}

	@Override
	public List<Symptom> searchSymptomybyAllergy(Integer allergyID) {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		try {
			String sql = "SELECT symptom.id, symptom.name, symptom.type FROM symptom INNER JOIN PRODUCES ON symptom.id=PRODUCES.symptom_id WHERE PRODUCES.allergy_id= ?";
			PreparedStatement p= c.prepareStatement(sql);
			p.setInt(1,allergyID);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Symptom s = new Symptom(id, name, type);
				symptoms.add(s);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return symptoms;
		
	}
		

}
