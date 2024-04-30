package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;
import java.sql.Date;

public interface PatientManager {
	
	//public Date selectVisitDate(String d);
	//public Date lookVisitDate(Integer id);	 //Del paciente
	
	public void addPatient(Patient p);
	public void modifyPatient(Patient p);
	public void deletePatient(Integer id);
	public Patient getPatientByID(Integer id);
	

	//Hacer los metodos de select by id y modify


}
