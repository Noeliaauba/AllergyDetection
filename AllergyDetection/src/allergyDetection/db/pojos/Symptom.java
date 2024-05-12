package allergyDetection.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Symptom implements Serializable{
	
	private static final long serialVersionUID = -3552642367592938308L;
	private Integer id;
	private String symptom_name;
	private String symptom_type;
	private List<Patient> patients;
	private List<Allergy> allergies;
	
	
	public Symptom () {
		this.patients = new ArrayList<Patient>();
		this.allergies = new ArrayList<Allergy>();

	}
	
	public Symptom (String _symptom_name, String _symptom_type) {
		this.symptom_name= _symptom_name;
		this.symptom_type=_symptom_type;
	}
	
	public Symptom (Integer _id, String _symptom_name, String _symptom_type) {
		this.id=_id;
		this.symptom_name= _symptom_name;
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
	 public String getSymptom_name() {
			return symptom_name;
		}

		public void setSymptom_name(String symptom_name) {
			this.symptom_name = symptom_name;
		}
	 public String getSymptomType() {
		 return symptom_type;
	 }
	 public void setSymptomType(String _symptom_type) {
		 this.symptom_type= _symptom_type;
	 }
	 
	 public String toString() {
	    	return "Symptom [id=" + id + ", symptom name= " + symptom_name +" symptom type=" + symptom_type + "]";
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
