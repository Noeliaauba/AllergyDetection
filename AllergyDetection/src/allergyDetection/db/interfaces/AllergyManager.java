package allergyDetection.db.interfaces;
import java.util.*;
import allergyDetection.db.pojos.Allergy;
import allergyDetection.db.pojos.Treatment;

public interface AllergyManager {
	public void addAllergy(Allergy a);
	public void modifyAllergy(Allergy a);
	public List<Allergy> searchAllergybyTreatment(Treatment t);
	public List<Allergy> showAllAllergy();
	

}
