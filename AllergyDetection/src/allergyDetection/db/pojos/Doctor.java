package allergyDetection.db.pojos;



public class Doctor {
 
	 private Integer id;
	 private String name;
	 
	 public Doctor (Integer _id, String _name) {
		    this.id= _id;
			this.name = _name;
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
