package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Patient;
public interface DoctorManager {
	public void addPatient(Patient p);
	public void modifyPatient(Integer id);
	public void deletePatient(Integer id);
	public Patient searchPatient(Integer id);
}
