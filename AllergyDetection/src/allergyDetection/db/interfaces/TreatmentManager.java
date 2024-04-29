package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Treatment;
public interface TreatmentManager {
	public void addTreatment(Treatment t); 
	public void modifyTreatment(Integer id); //Del paciente
	public void deleteTreatment(Integer id);
	//Search Treatmentfrompatientid
}
