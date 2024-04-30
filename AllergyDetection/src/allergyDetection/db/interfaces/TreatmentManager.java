package allergyDetection.db.interfaces;
import java.util.List;
import allergyDetection.db.pojos.Treatment;

public interface TreatmentManager {
	
	public void addTreatment(Treatment t); 
	public void modifyTreatment(Treatment t); //Del paciente
	public List<Treatment> searchTreatmentByType(String type);
}
