package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Prescription;

public interface PrescriptionManager {
	
	public void addPrescription(Prescription k);
	public void modifyPrescription(Prescription k);
	public void deletePrescription(Prescription k);
	public Prescription showPrescription(Integer id);
	
	public Prescription getPrescriptionById(Integer id); 
	//Del paciente
	
	//Hacer los metodos de select by id y modify
	

}
