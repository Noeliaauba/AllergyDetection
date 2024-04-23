package allergyDetection.db.pojos;

import java.util.Objects;

public class Doctor {
 
	 private Integer id;
	 private String name;
	 
	//private List <Prescription> prescriptions ; (Pues la relación entre doctor y prescription es 1-n ("un doctor tiene varias prescriptions")).		
		
	 
	 
	 public Doctor () {
	
	 }
	 
	 public Doctor (Integer _id, String _name) {
		    this.id= _id;
			this.name = _name;
			}
	
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
			Doctor other = (Doctor) obj;
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
		
		public String toString() {
			return "Doctor [id=" + id + ", name=" + name + "]";
		}

}
