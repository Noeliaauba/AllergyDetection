package allergyDetection.db.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import allergyDetection.db.interfaces.SymptomManager;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Symptom;

public class JCDBCSymptomManager implements SymptomManager {
	
	private Connection c;
	private ConnectionManager conMan;
	

	public JCDBCSymptomManager(ConnectionManager connectionManager) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void addSymptom(Symptom s) {
		try {
			String template = "INSERT INTO symptoms (name,type) VALUES (?,?)";
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
	        String query = "UPDATE symptom SET name = ?, type = ?,  WHERE id = ?";
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
	public List<Symptom> searchSymptomybyAllergy(Allergy a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Symptom> showAllSymptom() {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		try {
			String sql = "SELECT * FROM symptom";
			PreparedStatement p;
			p = c.prepareStatement(sql);
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
