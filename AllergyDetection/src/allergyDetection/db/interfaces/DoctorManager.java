package allergyDetection.db.interfaces;
import allergyDetection.db.pojos.Doctor;


public interface DoctorManager {
	public void addDoctor(Doctor d);
	public  Doctor getDoctorByID(Integer id);
	public Doctor getDoctorByusername(String username);
}
