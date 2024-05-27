package allergyDetection.db.interfaces;

import java.io.File;

import allergyDetection.db.pojos.Patient;

public interface XMLManager {

	 public void patient2XML(Patient p);
	 public Patient XML2patient (File xml);
	 public void patient2Html (Patient p);
	 
}
