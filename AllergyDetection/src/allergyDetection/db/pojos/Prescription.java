package allergyDetection.db.pojos;

import java.sql.Date;

public class Prescription {
	private Integer id;
	private Treatment treatment_required;
	private Boolean isUsed;
	private Patient given_to;
	private Doctor given_by;
	
	public Prescription (Integer _id, Treatment _treatment_required, Boolean _isUsed, Patient _given_to, Doctor _given_by) {
		this.id= _id;
		this.treatment_required = _treatment_required;
		this.isUsed=_isUsed;
		this.given_to = _given_to;
		this.given_by=_given_by;
		}
	 public Integer getId() {
	        return id;
	    }

	    public void setId(Integer _id) {
	        this.id = _id;
	    }

	    
	    public Treatment getTreatment_required() {
	        return treatment_required;
	    }

	    public void setTreatment_required(Treatment _treatment_required) {
	        this.treatment_required = _treatment_required;
	    }

	    
	    public Boolean getIsUsed() {
	        return isUsed;
	    }

	    public void setUsed(Boolean _isUsed) {
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
	    	return "Prescription [id=" + id + ", treatment required=" + treatment_required + ", ¿is used?=" + isUsed +", given to=" + given_to +", given by="+given_by+ "]";
	    }
	    

}
