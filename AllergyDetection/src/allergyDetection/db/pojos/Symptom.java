package allergyDetection.db.pojos;

public class Symptom {
	
	private Integer id;
	private String symptom_type;
	
	public Symptom (Integer _id, String _symptom_type) {
		this.id=_id;
		this.symptom_type=_symptom_type;
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
	    
}
