package allergyDetection.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Treatment implements Serializable{
	
	private static final long serialVersionUID = 2510589484868867965L;
	private Integer id;
	private String treatment_type;
	private String name;
	private List<Allergy> allergies;
	private List<Prescription> prescriptions;
	
	public Treatment () {
		this.allergies = new ArrayList<Allergy>();
	    this.prescriptions= new ArrayList<Prescription>();
	}
	public Treatment (String _name, String _treatmentType) {
		this.name=_name;
		this.treatment_type=_treatmentType;
	}

	public Treatment (Integer _id, String _name, String _treatmentType) {
		this.id=_id;
		this.name=_name;
		this.treatment_type=_treatmentType;
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
		Treatment other = (Treatment) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
	        return id;
	    }

	public void setId(Integer _id) {
	        this.id = _id;
	    }
	public String getName() {
		 return name;
	}
	
	public void setName(String _name) {
		 this.name=_name;
	}
	
	public String getTreatmentType() {
		 return treatment_type;
	 }
	 public void setTreatmentType(String _treatment_type) {
		 this.treatment_type= _treatment_type;
	 }
	 
	
	 public String toString() {
	    	return "Treatment [id=" + id + ", name= "+name + "]";
	    }

	public List<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Allergy> allergies) {
		this.allergies = allergies;
	}

	public List<Prescription> getPrescription() {
		return prescriptions;
	}

	public void setPrescription(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	    

}
