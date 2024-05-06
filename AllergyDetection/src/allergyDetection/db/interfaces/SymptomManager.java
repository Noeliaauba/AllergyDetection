package allergyDetection.db.interfaces;
import java.util.*;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;
public interface SymptomManager {
	
	public void addSymptom(Symptom s);
	public void modifySymptom(Symptom s);
	public List<Symptom> searchSymptomybyAllergy(Integer allergyID);	
	public List<Symptom> searchSymptom(String type_Symptom);
	
	// symMan.searchSympton("")
}
