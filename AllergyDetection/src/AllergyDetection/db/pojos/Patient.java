package AllergyDetection.db.pojos;

import java.sql.Date;
public class Patient {

		private Integer id;
		private String name;
		private Date dob;
		private String gender;
		
		
		public Patient (Integer _id, String _name, Date _dob, String _gender) {
			this.id= _id;
			this.name = _name;
			this.setDob(_dob);
			this.gender = _gender;
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
			return "Patient [id=" + id + ", name=" + name + ", dob=" + dob +", gender=" + gender + "]";
		}
}


