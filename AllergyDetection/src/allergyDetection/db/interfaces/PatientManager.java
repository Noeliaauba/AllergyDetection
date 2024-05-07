package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientManager {
	public void addPatient(Patient p);
	public void modifyPatient(Patient p);
	public void deletePatient(Integer id);
	public Patient getPatientByID(Integer id);
	    
	public void assignedAllergytoPatient(Integer patientId, Integer allergyId);
	public void assignedSymptomtoPatient(Integer patientId, Integer symptomId);
	//Hacer los metodos de select by id y modify


}
