package allergyDetection.db.pojos;

import java.sql.Date;
import java.util.Objects;
public class Patient {

		private Integer id;
		private String name;
		private Date dob;
		private String gender;
		
		public Patient () {
			
		}
	
		
		public Patient (Integer _id, String _name, Date _dob, String _gender) {
			this.id= _id;
			this.name = _name;
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

