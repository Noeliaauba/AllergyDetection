package allergyDetection.db.jdbc;


import java.sql.DriverManager;


import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import allergyDetection.db.interfaces.*;


public class ConnectionManager {
	private Connection c;
	private AllergyManager allergyManag;
	private DoctorManager doctorManag;
	private PatientManager patientManag;
	private PrescriptionManager prescriptionManag;
	private SymptomManager symptomManag;
	private TreatmentManager treatmentManag;
	

	public Connection getConnection() {
		return c;
	}
	
	public ConnectionManager() {
		this.connect();
		this.allergyManag = new JDBCAllergyManager(this);
		this.doctorManag = new JDBCDoctorManager(this);
		this.patientManag = new JDBCPatientManager(this);
		this.prescriptionManag = new JDBCPrescriptionManager(this);
		this.symptomManag =new JDBCSymptomManager(this);
		this.treatmentManag =new JDBCTreatmentManager(this);
		this.createTables();
	}
	
	private void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/allergyDetection.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		} catch (ClassNotFoundException cnfE) {
			System.out.println("Databases Allergy Detection not loaded");
			cnfE.printStackTrace();
		} catch (SQLException sqlE) {
			System.out.println("Error with database");
			sqlE.printStackTrace();
		}
	}
	
	public void close() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Error closing the database");
			e.printStackTrace();
		}
	}
	
	
	private void createTables() {
		try {
			
			Statement createTables1 = c.createStatement();
			String create1 = "CREATE TABLE patient ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " surname TEXT NOT NULL,"
					+ " dateOfBirth DATE,"
					+ " gender TEXT)";		
			createTables1.executeUpdate(create1);
			createTables1.close();
			
			
			Statement createTables2 = c.createStatement();
			String create2 = "CREATE TABLE doctor ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL"
					+ " surname TEXT NOT NULL)";
			createTables2.executeUpdate(create2);
			createTables2.close();
			
			Statement insertTables2 = c.createStatement();
			String insert2_1 = "INSERT INTO doctor (name, surname) VALUES ('Manuel', 'Garcia')";
			insertTables2.executeUpdate(insert2_1);
			String insert2_2 = "INSERT INTO doctor (name, surname) VALUES ('Juan', 'Urquijo')";
			insertTables2.executeUpdate(insert2_2);
			String insert2_3 = "INSERT INTO doctor (name, surname) VALUES ('Claudia', 'Gonzalez')";
			insertTables2.executeUpdate(insert2_3);
			insertTables2.close();
			
			
			Statement createTables3 = c.createStatement();
			String create3 = "CREATE TABLE allergy ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT ,"
					+ " name TEXT NOT NULL,"
					+ " type TEXT NOT NULL)";	
			createTables3.executeUpdate(create3);
			createTables3.close();
			

			Statement insertTables3 = c.createStatement();
			String insert3_1 = "INSERT INTO allergy (name, type) VALUES ('Dairy allergy', 'Alimentary')";
			insertTables3.executeUpdate(insert3_1);
			String insert3_2 = "INSERT INTO allergy (name, type) VALUES ('Pollen allergy', 'Stationary')";
			insertTables3.executeUpdate(insert3_2);
			String insert3_3 = "INSERT INTO allergy (name, type) VALUES ('Nut allergy', 'Alimentary')";
			insertTables3.executeUpdate(insert3_3);
			String insert3_4 = "INSERT INTO allergy (name, type) VALUES ('Dust Mite allergy', 'Stationary')";
			insertTables3.executeUpdate(insert3_4);
			String insert3_5 = "INSERT INTO allergy (name, type) VALUES ('Insect allergy', 'Insect')";
			insertTables3.executeUpdate(insert3_5);
			String insert3_6 = "INSERT INTO allergy (name, type) VALUES ('Drug allergy', 'Drugs')";
			insertTables3.executeUpdate(insert3_6);
			String insert3_7 = "INSERT INTO allergy (name, type) VALUES ('Sun allergy', 'Cutaneous')";
			insertTables3.executeUpdate(insert3_7);
			String insert3_8 = "INSERT INTO allergy (name, type) VALUES ('Gluten allergy', 'Alimentary')";
			insertTables3.executeUpdate(insert3_8);
			insertTables3.close();
			
			Statement createTables4 = c.createStatement();
			String create4 = "CREATE TABLE symptom ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " type TEXT)";		
			createTables4.executeUpdate(create4);
			createTables4.close();
			
			Statement insertTables5 = c.createStatement();
			String insert5_1 = "INSERT INTO symptom (name, type) VALUES ('Sneeze', 'Respiratory')";
			insertTables5.executeUpdate(insert5_1);
			String insert5_2 = "INSERT INTO symptom (name, type) VALUES ('Nasal Congestion', 'Respiratory')";
			insertTables5.executeUpdate(insert5_2);
			String insert5_3 = "INSERT INTO symptom (name, type) VALUES ('Cough', 'Respiratory')";
			insertTables5.executeUpdate(insert5_3);
			String insert5_4 = "INSERT INTO symptom (name, type) VALUES ('Runny Nose', 'Respiratory')";
			insertTables5.executeUpdate(insert5_4);
			String insert5_5 = "INSERT INTO symptom (name, type) VALUES ('Asthma Attack', 'Respiratory')";
			insertTables5.executeUpdate(insert5_5);
			String insert5_6 = "INSERT INTO symptom (name, type) VALUES ('Hives', 'Cutaneous')";
			insertTables5.executeUpdate(insert5_6);
			String insert5_7 = "INSERT INTO symptom (name, type) VALUES ('Itch ', 'Cutaneous')";
			insertTables5.executeUpdate(insert5_7);
			String insert5_8 = "INSERT INTO symptom (name, type) VALUES ('Burning Rush', 'Cutaneous')";
			insertTables5.executeUpdate(insert5_8);
			String insert5_9 = "INSERT INTO symptom (name, type) VALUES ('Abdominal Pain', 'Digestive')";
			insertTables5.executeUpdate(insert5_9);
			String insert5_10 = "INSERT INTO symptom (name, type) VALUES ('Nausea', 'Digestive')";
			insertTables5.executeUpdate(insert5_10);
			String insert5_11 = "INSERT INTO symptom (name, type) VALUES ('Swelling Mouth', 'Digestive')";
			insertTables5.executeUpdate(insert5_11);
			String insert5_12 = "INSERT INTO symptom (name, type) VALUES ('Tingling Tongue', 'Digestive')";
			insertTables5.executeUpdate(insert5_12);
			String insert5_13 = "INSERT INTO symptom (name, type) VALUES ('Chest Tightness', 'Others')";
			insertTables5.executeUpdate(insert5_13);
			String insert5_14 = "INSERT INTO symptom (name, type) VALUES ('Watery Eyes', 'Others')";
			insertTables5.executeUpdate(insert5_14);
			String insert5_15 = "INSERT INTO symptom (name, type) VALUES ('Fever', 'Others')";
			insertTables5.executeUpdate(insert5_15);
			String insert5_16 = "INSERT INTO symptom (name, type) VALUES ('Headache', 'Others')";
			insertTables5.executeUpdate(insert5_16);
			insertTables5.close();

			Statement createTables5 = c.createStatement();
			String create5 = "CREATE TABLE prescription ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " isUsed TEXT,"
					+ " given_to INTEGER,"
					+ " given_by INTEGER,"
					+ " FOREIGN KEY (given_to) REFERENCES patient(id),"
					+ " FOREIGN KEY (given_by) REFERENCES doctor(id))";
			createTables5.executeUpdate(create5);
			createTables5.close();
			
			Statement createTables6 = c.createStatement();
			String create6 = "CREATE TABLE treatment ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " type TEXT NOT NULL,"
					+ " prescription_id INTEGER REFERENCES prescription(id))";		
			createTables6.executeUpdate(create6);
			createTables6.close();
			
			Statement insertTables6 = c.createStatement();
			String insert6_1 = "INSERT INTO treatment (name, type) VALUES ('Desloratadina', 'Antihistamines')";
			insertTables6.executeUpdate(insert6_1);
			String insert6_2 = "INSERT INTO treatment (name, type) VALUES ('Dexametasona', 'Corticosteroids')";
			insertTables6.executeUpdate(insert6_2);
			String insert6_3 = "INSERT INTO treatment (name, type) VALUES ('Clorfenamina', 'Decongestants')";
			insertTables6.executeUpdate(insert6_3);
			String insert6_4 = "INSERT INTO treatment (name, type) VALUES ('Albuterol', 'Bronchodilators')";
			insertTables6.executeUpdate(insert6_4);
			String insert6_5 = "INSERT INTO treatment (name, type) VALUES ('Epinefrina', 'Vaccine')";
			insertTables6.executeUpdate(insert6_5);
			String insert6_6 = "INSERT INTO treatment (name, type) VALUES ('Prednisona', 'Corticosteroids')";
			insertTables6.executeUpdate(insert6_6);
			String insert6_7 = "INSERT INTO treatment (name, type) VALUES ('Oximetazolina', 'Decongestants')";
			insertTables6.executeUpdate(insert6_7);
			String insert6_8 = "INSERT INTO treatment (name, type) VALUES ('Metaproterenol', 'Bronchodilators')";
			insertTables6.executeUpdate(insert6_8);
			insertTables6.close();
			
			Statement createTables7 = c.createStatement();
			String create7 = "CREATE TABLE HAS ( "
					+ " patient_id INTEGER REFERENCES patient(id),"
					+ " symptom_id INTEGER REFERENCES symptom(id),"
					+ " PRIMARY KEY (patient_id, symptom_id))";
			createTables7.executeUpdate(create7);
			createTables7.close();
			
			Statement createTables8 = c.createStatement();
			String create8 = "CREATE TABLE PRODUCES ( "
					+ " symptom_id INTEGER REFERENCES symptom(id),"
					+ " allergy_id INTEGER REFERENCES allergy(id),"
					+ " PRIMARY KEY (symptom_id, allergy_id))";
			createTables8.executeUpdate(create8);
			createTables8.close();
			
			
			Statement createTables9 = c.createStatement();
			String create9 = "CREATE TABLE SUFFERS ( "
					+ " patient_id INTEGER REFERENCES patient(id),"
					+ " allergy_id INTEGER REFERENCES allergy(id),"
					+ " PRIMARY KEY (patient_id, allergy_id))";
			createTables9.executeUpdate(create9);
			createTables9.close();
			
			
			Statement createTables10 = c.createStatement();
			String create10 = "CREATE TABLE OWNS ( "
					+ " allergy_id INTEGER REFERENCES allergy(id),"
					+ " treatment_id INTEGER REFERENCES treatment(id),"
					+ " PRIMARY KEY (treatment_id,allergy_id))";
			createTables10.executeUpdate(create10);
			createTables10.close();
			
			
					
	} catch (SQLException sqlE) {
			if (sqlE.getMessage().contains("already exist")){
				System.out.println("No need to create the tables; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}
		}
	}

	public AllergyManager getAllergy() {
		return allergyManag;
	}

	public DoctorManager getDoctor() {
		return doctorManag;
	}

	public PatientManager getPatient() {
		return patientManag;
	}

	public PrescriptionManager getPrescription() {
		return prescriptionManag;
	}

	public SymptomManager getSymptom() {
		return symptomManag;
	}

	public TreatmentManager getTreatment() {
		return treatmentManag;
	}

	public Object getPatientManager() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}