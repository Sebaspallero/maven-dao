package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.DB;
import dao.IDao;
import model.Patient;

public class PatienDaoH2 implements IDao <Patient>{

    private static final Logger LOGGER = LogManager.getLogger(PatienDaoH2.class);

    private static final String SQL_INSERT = "INSERT INTO PATIENT (CARDIDENTITY, NAME, LASTNAME, ADMISSIONDATE) VALUES (?,?,?,?)";
    private static final String SQL_SELECT = "SELECT ID, CARDIDENTITY, NAME, LASTNAME, ADMISSIONDATE FROM PATIENT WHERE ID = ?";
    private static final String SQL_UPADTE = "UPDATE PATIENT SET CARDIDENTITY = ?, NAME = ?, LASTNAME = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM PATIENT WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT ID, CARDIDENTITY, NAME, LASTNAME, ADMISSIONDATE FROM PATIENT";

    @Override
    public Patient save(Patient patient) {

        Connection connection = null;

        try {
            LOGGER.info("An operation to save a Patient has started");

            //CONVERTIMOS LA FECHA DE TIPO UTIL A TIPO SQL
            java.sql.Date sqlDate = new java.sql.Date(patient.getAdmissionDate().getTime());

            //CONECTAR A LA DB
            connection = DB.getConnection();

            //INSERTAR VALORES EN LA TABLA
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, patient.getCardIdentity());
            psInsert.setString(2, patient.getName());
            psInsert.setString(3, patient.getLastName());
            psInsert.setDate(4, sqlDate);

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();

            while (resultSet.next()) {
                patient.setId(resultSet.getInt(1));
                LOGGER.info("This is the saved Patient: " + patient.getName() + " " + patient.getLastName());
            }

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return patient;
    }

    @Override
    public Patient findById(int id) {
        
        Connection connection = null;
        LOGGER.info("The search for a Patient has started");

        Patient Patient = null;

        try {

            connection = DB.getConnection();

            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);

            ResultSet resultSet = psSelect.executeQuery();

            while (resultSet.next()) {
                Patient = new Patient(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5));
                LOGGER.info("The Patient with ID: " + Patient.getId() + " was found: " + Patient.getName() + " " + Patient.getLastName());
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return Patient;
    }

    @Override
    public void update(Patient patient) {
        
        LOGGER.info("Updating a Patient");
        Connection connection = null;

        try {
            
            connection = DB.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPADTE);
            psUpdate.setInt(1, patient.getCardIdentity());
            psUpdate.setString(2, patient.getName());
            psUpdate.setString(3, patient.getLastName());
            psUpdate.setInt(4, patient.getId());

            psUpdate.execute();

            LOGGER.info("The Patient with id " + patient.getId() + " was updated succesfully");

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) {
        
        Connection connection = null;
       
        try {
            connection = DB.getConnection();

            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);

            psDelete.execute();
            LOGGER.warn("The Patient with ID " + id + " was deleted succesfullly");

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Patient> findAll() {
        
        LOGGER.info("Searchig all Patients");

        List<Patient> patientList = new ArrayList<>();
        Patient patient = null;

        Connection connection = null;

        try {
            connection = DB.getConnection();
            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECT_ALL);

            //GUARDAMOS EN UN RESULTSET TODOS LOS ODONTOLOGOS, PARA LUEGO AGREGAR ESOS DATOS
            // A UN OBJETO Patient Y ESE OBJETO AGREGARLO A LA LISTA
            ResultSet resultSet = psSelectAll.executeQuery();

            while (resultSet.next()) {
                patient = new Patient(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5));
                patientList.add(patient);
                LOGGER.info("The Patient with ID: " + patient.getId() + " was found " + 
                            " Registration: " + patient.getCardIdentity() + 
                            " Name: " + patient.getName() + 
                            " Last Name: " + patient.getLastName());
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
        } finally{
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
            }
        }

        return patientList;
    }

}
