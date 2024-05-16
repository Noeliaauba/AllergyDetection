package allergyDetection.db.interfaces;

import allergyDetection.db.pojos.Patient;
import java.util.List;

public interface PatientManager {
	public void addPatient(Patient p);
	public void modifyPatient(Patient p);
	public void deletePatient(Integer id);
	public Patient getPatientByID(Integer id); 
	public Patient getPatientByusername(String username);
	public List<Patient> searchPatient(String name_Patient);
	public void assignedAllergytoPatient(Integer patientId, Integer allergyId);
	public void assignedSymptomtoPatient(Integer patientId, Integer symptomId);


}
