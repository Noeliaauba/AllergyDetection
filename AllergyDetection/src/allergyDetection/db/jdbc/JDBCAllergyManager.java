package allergyDetection.db.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.interfaces.AllergyManager;
import allergyDetection.db.pojos.Allergy;
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
			String template = "INSERT INTO allergies (name,type) VALUES (?,?)";
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
	        String query = "UPDATE allergies SET name = ?, type = ?, startDate=?, endDate=?, WHERE id = ?";
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setString(1, a.getAllergyName());
	        pstmt.setString(2, a.getAllergyType()); 
	        pstmt.setDate(3, a.getStartDateAllergy());
	        pstmt.setDate(4, a.getEndDateAllergy());
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
	 public List<Allergy> searchAllergybyTreatment(Treatment t) {
		return null;
		/*List<Allergy> allergylist = new ArrayList<Allergy>();
		try {
			String sql = "SELECT * FROM allergies WHERE  LIKE ?";
			PreparedStatement p;
			p = c.prepareStatement(sql);
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
		return lista; */
	}

	
	
	public List<Allergy> showAllAllergy() {
		List<Allergy> allergies = new ArrayList<Allergy>();
		try {
			String sql = "SELECT * FROM allergies";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Date startDate=rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				Allergy a = new Allergy(id, name, type, startDate, endDate);
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
}


