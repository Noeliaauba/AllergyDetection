package allergyDetection.db.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Treatment {
	
	private Integer id;
	private String treatment_type;
	private String name;
	private Prescription prescription;
	private List<Allergy> allergies;
	
	
	public Treatment () {
		this.allergies = new ArrayList<Allergy>();
	
	}
	
	public Treatment (Integer _id, String _name, String _treatmentType,Prescription _prescriptionreq) {
		this.id=_id;
		this.name=_name;
		this.treatment_type=_treatmentType;
		this.prescription=_prescriptionreq;
	}
	
	public Treatment (Integer _id, String _name, String _treatmentType) {
		this.id=_id;
		this.name=_name;
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
	    	return "Treatment [id=" + id + ", treatment type=" + treatment_type + "]";
	    }

	public List<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Allergy> allergies) {
		this.allergies = allergies;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	    

}
