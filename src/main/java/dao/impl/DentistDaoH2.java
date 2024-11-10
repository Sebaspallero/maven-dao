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

import model.Dentist;

public class DentistDaoH2 implements IDao<Dentist>{

    private static final Logger LOGGER = LogManager.getLogger(DentistDaoH2.class);

    private static final String SQL_INSERT = "INSERT INTO DENTIST (REGISTRATION, NAME, LASTNAME) VALUES (?,?,?)";
    private static final String SQL_SELECT = "SELECT ID, REGISTRATION, NAME, LASTNAME FROM DENTIST WHERE ID = ?";
    private static final String SQL_UPADTE = "UPDATE DENTIST SET REGISTRATION = ?, NAME = ?, LASTNAME = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM DENTIST WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT ID, REGISTRATION, NAME, LASTNAME FROM DENTIST";

    @Override
    public Dentist save(Dentist dentist) {

        Connection connection = null;

        try {
            LOGGER.info("An operation to save a Dentist has started");

            //CONECTAR A LA DB
            connection = DB.getConnection();

            //INSERTAR VALORES EN LA TABLA
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, dentist.getRegistration());
            psInsert.setString(2, dentist.getName());
            psInsert.setString(3, dentist.getLastName());

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();

            while (resultSet.next()) {
                dentist.setId(resultSet.getInt(1));
                LOGGER.info("This is the saved Dentist: " + dentist.getName() + " " + dentist.getLastName());
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

        return dentist;
    }

    @Override
    public Dentist findById(int id) {
        
        Connection connection = null;
        LOGGER.info("The search for a Dentist has started");

        Dentist dentist = null;

        try {

            connection = DB.getConnection();

            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);

            ResultSet resultSet = psSelect.executeQuery();

            while (resultSet.next()) {
                dentist = new Dentist(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
                LOGGER.info("The dentist with ID: " + dentist.getId() + " was found: " + dentist.getName() + " " + dentist.getLastName());
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

        return dentist;
    }

    @Override
    public void update(Dentist dentist) {
        
        LOGGER.info("Updating a Dentist");
        Connection connection = null;

        try {
            
            connection = DB.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPADTE);
            psUpdate.setInt(1, dentist.getRegistration());
            psUpdate.setString(2, dentist.getName());
            psUpdate.setString(3, dentist.getLastName());
            psUpdate.setInt(4, dentist.getId());

            psUpdate.execute();

            LOGGER.info("The dentist with id " + dentist.getId() + " was updated succesfully");

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
            LOGGER.warn("The Dentist with ID " + id + " was deleted succesfullly");

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
    public List<Dentist> findAll() {
        
        LOGGER.info("Searchig all Dentists");

        List<Dentist> dentistList = new ArrayList<>();
        Dentist dentist = null;

        Connection connection = null;

        try {
            connection = DB.getConnection();
            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECT_ALL);

            //GUARDAMOS EN UN RESULTSET TODOS LOS ODONTOLOGOS, PARA LUEGO AGREGAR ESOS DATOS
            // A UN OBJETO DENTIST Y ESE OBJETO AGREGARLO A LA LISTA
            ResultSet resultSet = psSelectAll.executeQuery();

            while (resultSet.next()) {
                dentist = new Dentist(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
                dentistList.add(dentist);
                LOGGER.info("The dentist with ID: " + dentist.getId() + " was found " + 
                            " Registration: " + dentist.getRegistration() + 
                            " Name: " + dentist.getName() + 
                            " Last Name: " + dentist.getLastName());
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

        return dentistList;
    }

}
