package allergyDetection.db.interfaces;

import java.sql.Date;

public interface PatientManager {
	
	public void selectVisitDate();
	
	public Date lookVisitDate();
	
	public String showMedicalScore();
	
	public String showPrescription();
	
	public void seePrescription();
	


}
