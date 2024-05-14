package allergyDetection.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Patient implements Serializable {

		private static final long serialVersionUID = 5415639098561047229L;
		private Integer id;
		private String name;
		private String surname;
		private Date dob;
		private String gender;
		
		private List <Prescription> prescriptions; 
		private List <Allergy> allergies;
		private List<Symptom> symptoms;
		
		
		public Patient () {
			this.prescriptions = new ArrayList<Prescription>();
			this.allergies = new ArrayList<Allergy>();
			this.symptoms = new ArrayList<Symptom>();
			
		}
	
		public Patient  (String _name,String _surname, Date _dob, String _gender) {
			this.name = _name;
			this.surname=_surname;
			this.setDob(_dob);
			this.gender = _gender;
			}
		
		public Patient (Integer _id, String _name,  String _surname, Date _dob, String _gender) {
			this.id= _id;
			this.name = _name;
			this.surname=_surname;
			this.setDob(_dob);
			this.gender = _gender;
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
			Patient other = (Patient) obj;
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
			this.name = _name;
		}
	
		public String getSurname() {
			return surname;
		}
		
		public void setSurname(String _surname) {
			this.surname = _surname;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date _dob) {
			this.dob = _dob;
		}
		
		public String getGender() {
			return gender;
		}

		public void setGender(String _gender) {
			this.gender = _gender;
		}
		public String toString() {
			return "Patient [id=" + id + ", name=" + name + ", surname=" + id +" dob=" + dob +", gender=" + gender + "]";
		}


		public List <Prescription> getPrescriptions() {
			return prescriptions;
		}


		public void setPrescriptions(List <Prescription> prescriptions) {
			this.prescriptions = prescriptions;
		}


		public List <Allergy> getAllergies() {
			return allergies;
		}


		public void setAllergies(List <Allergy> allergies) {
			this.allergies = allergies;
		}


		public List<Symptom> getSymptoms() {
			return symptoms;
		}


		public void setSymptoms(List<Symptom> symptoms) {
			this.symptoms = symptoms;
		}
}


