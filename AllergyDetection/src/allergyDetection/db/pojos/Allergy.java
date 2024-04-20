package allergyDetection.db.pojos;

import java.sql.Date;

public class Allergy {

	private Integer allergyID;
	private String allergyName;
	private String allergyType;	//i think it is better to do an enum with all allergy type that can be,
								// so we will need to do another class enum called allergyType...
//Maybe we can do private List<Symtoms_related> Symptoms;
	// Additional method to add to a list
	private Date startDateAllergy;
	private Date endDateAllergy;

	
	public Allergy(Integer _allergyID,String _allergyName, String _allergyType,Date _startDateAllergy,Date _endDateAllergy) {
		this.allergyID=_allergyID;
		this.allergyName=_allergyName;
		this.allergyType=_allergyType;
		this.startDateAllergy=_startDateAllergy;
		this.endDateAllergy=_endDateAllergy;
	}
	
	
	public Integer getAllergyID() {
		return allergyID;
	}
	public void setAllergyID(Integer allergyID) {
		this.allergyID=allergyID;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName=allergyName;
	}
	public String getAllergyType() {
		return allergyType;
	}
	public void setAllergyType(String allergyType) {
		this.allergyType=allergyType;
	}
	public Date getStartDateAllergy() {
		return startDateAllergy;
	}
	public void setStartDateAllergy(Date startDateAllergy) {
		this.startDateAllergy=startDateAllergy;
	}
	public Date getEndDateAllergy() {
		return endDateAllergy;
	}
	public void setEndDateAllergy(Date endDateAllergy) {
		this.endDateAllergy= endDateAllergy;
	}
	
	@Override
	public String toString(){
		return "Allergy [allergyID="+allergyID+
				", allergyName="+allergyName+
				", allergyType="+allergyType+
				", startDateAllergy="+startDateAllergy+
				", endDateAllergy="+endDateAllergy+ "]";
				}
	
}
