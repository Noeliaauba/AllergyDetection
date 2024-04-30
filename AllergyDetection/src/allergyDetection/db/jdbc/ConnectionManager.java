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
	//INSERTS PARA ALLERGIES
	
	private void createTables() {
		try {
			
			Statement createTables1 = c.createStatement();
			
			String create1 = "CREATE TABLE patient ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL,"
					+ " dateOfBirth DATE,"
					+ " gender TEXT)";
					
			createTables1.executeUpdate(create1);
			createTables1.close();
			
			Statement createTables2 = c.createStatement();
			
			String create2 = "CREATE TABLE doctor ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL)";
		
			createTables2.executeUpdate(create2);
			createTables2.close();
			
			Statement createTables3 = c.createStatement();
			
			String create3 = "CREATE TABLE allergy ( "
					+ " id INTEGER PRIMARY KEY ,"
					+ " startDate DATE,"
					+ " endDate DATE,"
					+ " name TEXT NOT NULL,"
					+ " type TEXT NOT NULL)";
					
			createTables3.executeUpdate(create3);
			createTables3.close();
			
			Statement createTables4 = c.createStatement();
			
			
			String create4 = "CREATE TABLE prescription ( "
					+ " id INTEGER ,"
					+ " treatment_required INTEGER REFERENCES treatment(id),"
					+ " isUsed BOOLEAN,"
					+ " given_to INTEGER REFERENCES patient(id),"
					+ " given_by INTEGER REFERENCES doctor(id),"
					+ " PRIMARY KEY (patient_id, doctor_id))";
			
			createTables4.executeUpdate(create4);
			createTables4.close();
			
			Statement createTables5 = c.createStatement();
			String create5 = "CREATE TABLE symptom ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL,"
					+ " type TEXT)";
					
			createTables5.executeUpdate(create5);
			createTables5.close();
			
			Statement createTables6 = c.createStatement();
			String create6 = "CREATE TABLE treatment ( "
					+ " id INTEGER PRIMARY KEY,"
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