package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Symptom;
public interface SymptomManager {
	public void addSymptom(Symptom s);
	public void modifySymptom(Symptom s);
	public void deleteSymptom(Symptom s);
	
}
