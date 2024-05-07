package allergyDetection.db.pojos;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Allergy {

	private Integer allergyID;
	private String allergyName;
	private String allergyType;	
	private List<Patient> patients;
	private List <Treatment> treatments;
	private List<Symptom> symptoms;

	public Allergy() {
	}
	
	
	public Allergy(Integer _allergyID,String _allergyName, String _allergyType) {
		this.allergyID=_allergyID;
		this.allergyName=_allergyName;
		this.allergyType=_allergyType;
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
	
	
	@Override
	public String toString(){
		return "Allergy [allergyID="+allergyID+
				", allergyName="+allergyName+
				", allergyType="+allergyType+"]";
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
