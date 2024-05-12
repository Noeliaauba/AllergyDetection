package allergyDetection.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prescription implements Serializable {
	
	
	private static final long serialVersionUID = 2251803222173073799L;
	private Integer id;
	private String isUsed;
	private Patient given_to;
	private Doctor given_by;
	
	private List<Treatment> treatments;
	
	private Doctor doctor;
	
	private Patient patient;
	
	public Prescription() {
		this.treatments = new ArrayList<Treatment>();
	}
	
	public Prescription (Integer _id, String _isUsed, Patient _given_to, Doctor _given_by) {
		this.id= _id;
		this.isUsed=_isUsed;
		this.given_to = _given_to;
		this.given_by=_given_by;
		}
	
	public Prescription (String _isUsed, Patient _given_to, Doctor _given_by) {
		this.isUsed=_isUsed;
		this.given_to = _given_to;
		this.given_by=_given_by;
		}
	public Prescription (Patient _given_to, Doctor _given_by) {
		this.given_to=_given_to;
		this.given_by=_given_by;
	}
	
	 public Integer getId() {
	        return id;
	    }

	    public void setId(Integer _id) {
	        this.id = _id;
	    }

	   	    
	    public String getIsUsed() {
	        return isUsed;
	    }

	    public void setUsed(String _isUsed) {
	        isUsed = _isUsed;
	    }

	    
	    public Patient getGiven_To() {
	        return given_to;
	    }

	    public void setGiven_to(Patient _given_to) {
	        this.given_to = _given_to;
	    }

	    
	    public Doctor getGiven_by() {
	        return given_by;
	    }

	    public void setGivenBy(Doctor _given_by) {
	        this.given_by = _given_by;
	    }

	    
	    @Override
	    public String toString() {
	    	return "Prescription [id=" + id + ", ¿is used?=" + isUsed +", given to=" + given_to +", given by="+given_by+ "]";
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
			Prescription other = (Prescription) obj;
			return Objects.equals(id, other.id);
		}

		public Patient getPatient() {
			return patient;
		}

		public void setPatient(Patient patient) {
			this.patient = patient;
		}

		public Doctor getDoctor() {
			return doctor;
		}

		public void setDoctor(Doctor doctor) {
			this.doctor = doctor;
		}

		public List<Treatment> getTreatments() {
			return treatments;
		}

		public void setTreatments(List<Treatment> treatments) {
			this.treatments = treatments;
		}

}
