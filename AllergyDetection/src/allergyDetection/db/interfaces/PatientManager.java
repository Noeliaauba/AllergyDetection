package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Prescription;
import java.sql.Date;

public interface PatientManager {
	public Date selectVisitDate(String d);
	public Date lookVisitDate(Integer id);	 //Del paciente
	public Prescription showPrescription(Integer id);  //Del paciente



}
