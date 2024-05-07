package allergyDetection.db.interfaces;
import java.util.List;

import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Prescription;

public interface PrescriptionManager {
	
	public void addPrescription(Prescription k);
	public void modifyPrescription(Prescription k);
	public void deletePrescription(Integer id);
	public Prescription getPrescriptionById(Integer id); 
	public List <Prescription> searchPrescriptionByPatient(Integer patientid);
	public List <Prescription> searchPrescriptionByDoctor(Integer doctorid);
	
	//Hacer los metodos de select by id y modify
	

}
