package allergyDetection.db.interfaces;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Treatment;

public interface PrescriptionManager {
	
	public void addPrescription(Prescription k);
	public void modifyPrescription(Prescription k);
	public void deletePrescription(Integer id);
	public Prescription getPrescriptionById(Integer id); 
	public List <Prescription> searchPrescriptionByPatient(Integer patientid);
	public List <Prescription> searchPrescriptionByDoctor(Integer doctorid);
	
	//Hacer prescription by treatment
	/*@Override
public List<Treatment> searchTreatmentByPrescription(Integer prescriptionID){
	List<Treatment> treatments = new ArrayList<Treatment>();
	
	try {
		String sql = "SELECT * FROM treatment WHERE prescription_id = " + prescriptionID;
	    PreparedStatement p = c.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String type= rs.getString("type");
			Prescription pres= conMan.getPrescription().getPrescriptionById(prescriptionID);
			Treatment trs = new Treatment(id, name, type, pres);
			treatments.add(trs);
		
		}
		rs.close();
		p.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return treatments;
}
*/
	

}
