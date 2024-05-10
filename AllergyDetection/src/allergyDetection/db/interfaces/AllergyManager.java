package allergyDetection.db.interfaces;
import java.util.*;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Symptom;
import allergyDetection.db.pojos.Treatment;

public interface AllergyManager {
	public void addAllergy(Allergy a);
	public void modifyAllergy(Allergy a);
	public Allergy getAllergyByID(Integer id);
	public List<Allergy> searchAllergy(String type_Allergy);
	public List<Allergy> searchAllergybyPatient(Integer patientID);	
	//public List<Allergy> searchAllergybyTreatment(Integer treatmentId);
	public void assignedSymptomtoAllergy(Integer symptomId, Integer allergyId);
	public void assignedTreatmenttoAllergy(Integer treatmentId, Integer allergyId);
	// allMan.searchAllergybyTreatment(t.getId());

}
