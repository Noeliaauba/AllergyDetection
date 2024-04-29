package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Allergy;
public interface AllergyManager {
	public void addAllergy(Allergy a);
	public void modifyAllergy(Allergy a);
	public void deleteAllergy(Allergy a);

}
