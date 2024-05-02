package allergyDetection.db.interfaces;
import java.util.*;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;
public interface SymptomManager {
	
	public void addSymptom(Symptom s);
	public void modifySymptom(Symptom s);
	public List<Symptom> searchSymptomybyAllergy(Allergy a);
	public List<Symptom> showAllSymptom();
}
