package allergyDetection.db.pojos;

public class Treatment {
	
	private Integer id;
	private String treatment_type;
	
	public Treatment (Integer _id, String _treatment_type) {
		this.id=_id;
		this.treatment_type=_treatment_type;
	}
	
	 public Integer getId() {
	        return id;
	    }

	 public void setId(Integer _id) {
	        this.id = _id;
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
	    

}
