package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import java.sql.Date;

public interface PatientManager {
	public void addPatient(Patient p);
	public void modifyPatient(Patient p);
	public void deletePatient(Integer id);
	public Patient getPatientByID(Integer id);
	public void assignedAllergytoPatient(Integer patientId, Integer allergyId);
	

	//Hacer los metodos de select by id y modify


}
