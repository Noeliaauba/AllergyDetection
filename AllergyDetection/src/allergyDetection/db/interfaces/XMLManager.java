package allergyDetection.db.interfaces;

import java.io.File;


import allergyDetection.db.pojos.Doctor;
import allergyDetection.db.pojos.Patient;

public interface XMLManager {

	 public File patient2XML(Patient p);
	 public Patient XML2patient (File xml);
	 public void patient2Html (Patient p);
	 
	 public File doctor2XML(Doctor d);
	 public Doctor XML2doctor (File xml);
	 public void doctor2Html (Doctor p);
	 
}
