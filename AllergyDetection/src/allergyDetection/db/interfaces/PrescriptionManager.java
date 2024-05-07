package allergyDetection.db.interfaces;
import java.util.List;
import allergyDetection.db.pojos.Prescription;

public interface PrescriptionManager {
	
	public void addPrescription(Prescription k);
	public void modifyPrescription(Prescription k);
	public void deletePrescription(Prescription k);
	public List<Prescription> searchPrescriptionByUse(String is_Used);
	public Prescription getPrescriptionById(Integer id); 
	//Del paciente
	
	//Hacer los metodos de select by id y modify
	

}
