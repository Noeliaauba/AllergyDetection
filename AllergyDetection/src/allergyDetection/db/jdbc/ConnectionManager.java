package allergyDetection.db.jdbc;


import java.sql.DriverManager;


import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import allergyDetection.db.interfaces.*;


public class ConnectionManager {
	private Connection c;
	private AllergyManager allergy;
	private DoctorManager doctor;
	private PatientManager patient;
	private PrescriptionManager prescription;
	private SymptomManager symptom;
	private TreatmentManager treatment;
	

	public Connection getConnection() {
		return c;
	}
	
	public ConnectionManager() {
		this.connect();
		this.allergy = new JDBCAllergyManager(this);
		this.doctor = new JDBCDoctorManager(this);
		this.patient = new JDBCPatientManager(this);
		this.prescription = new JDBCPrescriptionManager(this);
		this.symptom=new JCDBCSymptomManager(this);
		this.treatment=new JDBCTreatmentManager(this);
		this.createTables();
	}
	
	private void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/library.db");
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
			String create1 = "CREATE TABLE patients ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL,"
					+ " dateOfBirth DATE,"
					+ " gender TEXT)";		
			createTables1.executeUpdate(create1);
			createTables1.close();
			
			
			Statement createTables2 = c.createStatement();
			String create2 = "CREATE TABLE doctors ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL)";
			createTables2.executeUpdate(create2);
			createTables2.close();
			
			
			Statement createTables3 = c.createStatement();
			String create3 = "CREATE TABLE allergys ( "
					+ " id INTEGER PRIMARY KEY ,"
					+ " startDate DATE,"
					+ " endDate DATE,"
					+ " name TEXT NOT NULL,"
					+ " type TEXT NOT NULL)";	
			createTables3.executeUpdate(create3);
			createTables3.close();
			Statement createTables4 = c.createStatement();
			
			
			String create4 = "CREATE TABLE prescriptions ( "
					+ " id INTEGER ,"
					+ " treatment_required INTEGER REFERENCES treatment(id),"
					+ " isUsed BOOLEAN,"
					+ " given_to INTEGER REFERENCES patient(id),"
					+ " given_by INTEGER REFERENCES doctor(id),"
					+ " PRIMARY KEY (patient_id, doctor_id))";
			createTables4.executeUpdate(create4);
			createTables4.close();
			
			
			Statement createTables5 = c.createStatement();
			String create5 = "CREATE TABLE symptoms ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL,"
					+ " type TEXT)";		
			createTables5.executeUpdate(create5);
			createTables5.close();
			
			
			Statement createTables6 = c.createStatement();
			String create6 = "CREATE TABLE treatments ( "
					+ " id INTEGER PRIMARY KEY,"
					+ "name TEXT NOT NULL,"
					+ " type TEXT)";		
			createTables6.executeUpdate(create6);
			createTables6.close();
			
			
			Statement createTables7 = c.createStatement();
			String create7 = "CREATE TABLE HAS ( "
					+ " patient_id INTEGER REFERENCES patient(id),"
					+ " symptom_id INTEGER REFERENCES symptom(id),"
					+ " startDate TEXT,"
					+ " endate TEXT),"
					+ " PRIMARY KEY (patient_id, symptom_id)";
			createTables7.executeUpdate(create7);
			createTables7.close();
			
			
			Statement createTables8 = c.createStatement();
			String create8 = "CREATE TABLE PRODUCES ( "
					+ " symptom_id INTEGER REFERENCES symptom(id),"
					+ " allergy_id INTEGER REFERENCES allergy(id),"
					+ " PRIMARY KEY (symptom_id, allergy_id)";
			createTables8.executeUpdate(create8);
			createTables8.close();
			
			
			Statement createTables9 = c.createStatement();
			String create9 = "CREATE TABLE SUFFERS ( "
					+ " patient_id INTEGER REFERENCES patient(id),"
					+ " allergy_id INTEGER REFERENCES allergy(id),"
					+ " PRIMARY KEY (patient_id, allergy_id)";
			createTables9.executeUpdate(create9);
			createTables9.close();
			
			
			Statement createTables10 = c.createStatement();
			String create10 = "CREATE TABLE OWNS ( "
					+ " allergy_id INTEGER REFERENCES allergy(id),"
					+ " treatment_id INTEGER REFERENCES allergy(name),"
					+ " PRIMARY KEY (allergy_id, treatment_id)";
			createTables10.executeUpdate(create10);
			createTables10.close();
			
			
			Statement insertTables3 = c.createStatement();
			String insert3_1 = "INSERT INTO allergies (name, type) VALUES ('Dairy', 'Alimentary')";
			insertTables3.executeUpdate(insert3_1);
			String insert3_2 = "INSERT INTO allergies (name, type) VALUES ('Pollen', 'Stationary')";
			insertTables3.executeUpdate(insert3_2);
			String insert3_3 = "INSERT INTO allergies (name, type) VALUES ('Nuit', 'Alimentary')";
			insertTables3.executeUpdate(insert3_3);
			String insert3_4 = "INSERT INTO allergies (name, type) VALUES ('Dust Mite', 'Statioanry')";
			insertTables3.executeUpdate(insert3_4);
			String insert3_5 = "INSERT INTO allergies (name, type) VALUES ('Insect', 'Insect')";
			insertTables3.executeUpdate(insert3_5);
			String insert3_6 = "INSERT INTO allergies (name, type) VALUES ('Drug', 'Drugs')";
			insertTables3.executeUpdate(insert3_6);
			String insert3_7 = "INSERT INTO allergies (name, type) VALUES ('Sun ', 'Cutaneous')";
			insertTables3.executeUpdate(insert3_7);
			String insert3_8 = "INSERT INTO allergies (name, type) VALUES ('Gluten', 'Alimentary')";
			insertTables3.executeUpdate(insert3_8);
			insertTables3.close();
			
			
			Statement insertTables5 = c.createStatement();
			String insert5_1 = "INSERT INTO symptoms (name, type) VALUES ('Sneeze', 'Respiratory')";
			insertTables5.executeUpdate(insert5_1);
			String insert5_2 = "INSERT INTO symptoms (name, type) VALUES ('Nasal Congestion', 'Respiratory')";
			insertTables5.executeUpdate(insert5_2);
			String insert5_3 = "INSERT INTO symptoms (name, type) VALUES ('Cough', 'Respiratory')";
			insertTables5.executeUpdate(insert5_3);
			String insert5_4 = "INSERT INTO symptoms (name, type) VALUES ('Runny Nose', 'Respiratory')";
			insertTables5.executeUpdate(insert5_4);
			String insert5_5 = "INSERT INTO symptoms (name, type) VALUES ('Asthma Attack', 'Respiratory')";
			insertTables5.executeUpdate(insert5_5);
			String insert5_6 = "INSERT INTO symptoms (name, type) VALUES ('Hives', 'Cutaneous')";
			insertTables5.executeUpdate(insert5_6);
			String insert5_7 = "INSERT INTO symptoms (name, type) VALUES ('Itch ', 'Cutaneous')";
			insertTables5.executeUpdate(insert5_7);
			String insert5_8 = "INSERT INTO symptoms (name, type) VALUES ('Burning Rush', 'Cutaneous')";
			insertTables5.executeUpdate(insert5_8);
			String insert5_9 = "INSERT INTO symptoms (name, type) VALUES ('Abdominal Pain', 'Digestive')";
			insertTables5.executeUpdate(insert5_9);
			String insert5_10 = "INSERT INTO symptoms (name, type) VALUES ('Nausea', 'Digestive')";
			insertTables5.executeUpdate(insert5_10);
			String insert5_11 = "INSERT INTO symptoms (name, type) VALUES ('Swelling Mouth', 'Digestive')";
			insertTables5.executeUpdate(insert5_11);
			String insert5_12 = "INSERT INTO symptoms (name, type) VALUES ('Tingling Tongue', 'Digestive')";
			insertTables5.executeUpdate(insert5_12);
			String insert5_13 = "INSERT INTO symptoms (name, type) VALUES ('Chest Tightness', 'Others')";
			insertTables5.executeUpdate(insert5_13);
			String insert5_14 = "INSERT INTO symptoms (name, type) VALUES ('Watery Eyes', 'Others')";
			insertTables5.executeUpdate(insert5_14);
			String insert5_15 = "INSERT INTO symptoms (name, type) VALUES ('Fever', 'Others')";
			insertTables5.executeUpdate(insert5_15);
			String insert5_16 = "INSERT INTO symptoms (name, type) VALUES ('Headache', 'Others')";
			insertTables5.executeUpdate(insert5_16);
			insertTables5.close();
			
			
			Statement insertTables6 = c.createStatement();
			String insert6_1 = "INSERT INTO treatments (name, type) VALUES ('Desloratadina', 'Antihistamines')";
			insertTables6.executeUpdate(insert6_1);
			String insert6_2 = "INSERT INTO treatments (name, type) VALUES ('Dexametasona', 'Corticosteroids')";
			insertTables6.executeUpdate(insert6_2);
			String insert6_3 = "INSERT INTO treatments (name, type) VALUES ('Clorfenamina', 'Decongestants')";
			insertTables6.executeUpdate(insert6_3);
			String insert6_4 = "INSERT INTO treatments (name, type) VALUES ('Albuterol', 'Bronchodilators')";
			insertTables6.executeUpdate(insert6_4);
			String insert6_5 = "INSERT INTO treatments (name, type) VALUES ('Epinefrina', 'Vaccine')";
			insertTables6.executeUpdate(insert6_5);
			String insert6_6 = "INSERT INTO treatments (name, type) VALUES ('Prednisona', 'Corticosteroids')";
			insertTables6.executeUpdate(insert6_6);
			String insert6_7 = "INSERT INTO treatments (name, type) VALUES ('Oximetazolina ', 'Decongestants')";
			insertTables6.executeUpdate(insert6_7);
			String insert6_8 = "INSERT INTO treatments (name, type) VALUES ('Metaproterenol', 'Bronchodilators')";
			insertTables6.executeUpdate(insert6_8);
			insertTables6.close();
			
					
	} catch (SQLException sqlE) {
			if (sqlE.getMessage().contains("Already exist")){
				System.out.println("No need to create the tables; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}
		}
	}

	public AllergyManager getAllergy() {
		return allergy;
	}

	public DoctorManager getDoctor() {
		return doctor;
	}

	public PatientManager getPatient() {
		return patient;
	}

	public PrescriptionManager getPrescription() {
		return prescription;
	}

	public SymptomManager getSymptom() {
		return symptom;
	}

	public TreatmentManager getTreatment() {
		return treatment;
	}
	
	
	
}