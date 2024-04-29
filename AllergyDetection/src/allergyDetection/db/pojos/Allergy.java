package allergyDetection.db.pojos;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Allergy {

	private Integer allergyID;
	private String allergyName;
	private String allergyType;	//i think it is better to do an enum with all allergy type that can be,
								// so we will need to do another class enum called allergyType...DO NOT DO ENUM
//Maybe we can do private List<Symtoms_related> Symptoms;
	// Additional method to add to a list
	private Date startDateAllergy;
	private Date endDateAllergy;
	
	private List<Patient> patients;
	private List <Treatment> treatments;
	private List<Symptom> symptoms;

	public Allergy() {
	}
	
	
	public Allergy(Integer _allergyID,String _allergyName, String _allergyType,Date _startDateAllergy,Date _endDateAllergy) {
		this.allergyID=_allergyID;
		this.allergyName=_allergyName;
		this.allergyType=_allergyType;
		this.startDateAllergy=_startDateAllergy;
		this.endDateAllergy=_endDateAllergy;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(allergyID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Allergy other = (Allergy) obj;
		return Objects.equals(allergyID, other.allergyID);
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


	public List<Patient> getPatients() {
		return patients;
	}


	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


	public List<Symptom> getSymptoms() {
		return symptoms;
	}


	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}


	public List <Treatment> getTreatments() {
		return treatments;
	}


	public void setTreatments(List <Treatment> treatments) {
		this.treatments = treatments;
	}
	
}
