package allergyDetection.db.jdbc;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.interfaces.*;
import allergyDetection.db.interfaces.TreatmentManager;

public class JDBCTreatmentManager implements TreatmentManager {
	
	private Connection c;
	private ConnectionManager conMan;

	public JDBCTreatmentManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
@Override
	public void addTreatment(Treatment t) {
		try {
			String template = "INSERT INTO treatment (name, type) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getTreatmentType());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}

}

@Override
public void modifyTreatment(Treatment t) {
	 try {
	        String query = "UPDATE treatments SET name = ?, type = ?  WHERE id = ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setString(1, t.getName());
	        pstmt.setString(2, t.getTreatmentType());
	        pstmt.close();
	        System.out.println("Treatment with ID " + t.getId() + " updated successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error in the database");
	        e.printStackTrace();
	    }
}

@Override
public List<Treatment> searchTreatmentByType(String typeParameter) {
	List<Treatment> lista = new ArrayList<Treatment>();
	try {
		String sql = "SELECT * FROM treatment WHERE type LIKE ?";
		PreparedStatement p= c.prepareStatement(sql);
		p.setString(1, "%" + typeParameter + "%");
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String type = rs.getString("type");
			Treatment t = new Treatment(id, name, type);
			lista.add(t);
		}
		rs.close();
		p.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return lista;
}

	
@Override
public Treatment getTreatmentById (Integer id) {
    try {
    	String sql = "SELECT * FROM treatment WHERE id = " + id;
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		Treatment t = new Treatment (rs.getInt("id"), rs.getString("name"),rs.getString("type"));
		return t;
        
    } catch (SQLException e) {
        System.out.println("Error in the database");
        e.printStackTrace();
    }
    return null;
}


public List<Treatment> searchTreatmentByAllergy(Integer allergyID) {
	List<Treatment> tratamientos = new ArrayList<Treatment>();
	try {
		String sql = "SELECT treatment.id, treatment.name, treatment.type FROM treatment INNER JOIN OWNS ON treatment.id=OWNS.treatment_id WHERE OWNS.allergy_id= ?";
		PreparedStatement p= c.prepareStatement(sql);
		p.setInt(1,allergyID);
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String type = rs.getString("type");
			Treatment t = new Treatment(id, name, type);
			tratamientos.add(t);
		}
		rs.close();
		p.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return tratamientos;
	
}
	
	
}


	
