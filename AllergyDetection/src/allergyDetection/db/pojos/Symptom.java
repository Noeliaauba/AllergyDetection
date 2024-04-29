package allergyDetection.db.pojos;

import java.util.List;
import java.util.Objects;

public class Symptom {
	
	private Integer id;
	private String symptom_type;
	
	private List<Patient> patients;
	
	private List<Allergy> allergies;
	
	
	public Symptom () {

	}
	
	public Symptom (Integer _id, String _symptom_type) {
		this.id=_id;
		this.symptom_type=_symptom_type;
	}
	
	
	
	 @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symptom other = (Symptom) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
	        return id;
	    }

	 public void setId(Integer _id) {
	        this.id = _id;
	    }
	 public String getSymptomType() {
		 return symptom_type;
	 }
	 public void setSymptomType(String _symptom_type) {
		 this.symptom_type= _symptom_type;
	 }
	 
	 public String toString() {
	    	return "Symptom [id=" + id + ", symptom type=" + symptom_type + "]";
	    }

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Allergy> allergies) {
		this.allergies = allergies;
	}
	    
}
