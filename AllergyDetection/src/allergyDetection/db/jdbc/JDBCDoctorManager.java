package allergyDetection.db.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import allergyDetection.db.interfaces.DoctorManager;
import allergyDetection.db.pojos.Doctor;


public class JDBCDoctorManager implements DoctorManager {
	
	private Connection c;
	private ConnectionManager conMan;

	public JDBCDoctorManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void addDoctor(Doctor d) {
		try {
			String template = "INSERT INTO doctor (name) VALUES (?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, d.getName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}

	@Override
	public Doctor getDoctorByID(Integer id) {
		try {
			String sql = "SELECT * FROM doctor WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Doctor d = new Doctor (rs.getInt("id"), rs.getString("name"));
			return d;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

}
