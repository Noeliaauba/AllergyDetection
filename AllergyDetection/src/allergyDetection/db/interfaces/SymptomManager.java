package allergyDetection.db.interfaces;
import java.util.*;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Patient;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;
public interface SymptomManager {
	
	public void addSymptom(Symptom s);
	public void modifySymptom(Symptom s);
	public void deleteSymptom(Integer id);
	public Symptom getSymptomByID(Integer id);
	public List<Symptom> searchSymptom(String type_Symptom);
	public List<Symptom> searchSymptomybyAllergy(Integer allergyID);	
	public List<Symptom> searchSymptombyPatient(Integer patientID);	
	
	// symMan.searchSymptonBY PATIENT("")
}
