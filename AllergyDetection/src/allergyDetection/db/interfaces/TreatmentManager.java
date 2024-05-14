package allergyDetection.db.interfaces;
import java.sql.*;
import java.util.List;
import allergyDetection.db.pojos.Prescription;
import allergyDetection.db.pojos.Treatment;

public interface TreatmentManager {
	
	public void addTreatment(Treatment t); 
	public void modifyTreatment(Treatment t); 
	public List<Treatment> searchTreatmentByType(String type);
	public List<Treatment> searchTreatmentByAllergy(Integer allergyid);
	public Treatment getTreatmentById(Integer id); 
	
}
