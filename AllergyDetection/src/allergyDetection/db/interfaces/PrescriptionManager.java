package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Prescription;
public interface PrescriptionManager {
	public void addPrescription(Prescription k);
	public void modifyPrescription(Integer id);
	public void deletePrescription(Integer id);

}
