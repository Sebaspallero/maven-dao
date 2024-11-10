
import dao.DB;
import dao.impl.DentistDaoH2;
import dao.impl.PatienDaoH2;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Dentist;
import model.Patient;
import service.DentistService;
import service.PatientService;

public class Main {
    
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args){

        //INICIALIZAMOS EL SERVICIO USANDO EL DAO DE DENTISTH2 Y PATIENH2
        DentistService dentistService = new DentistService(new DentistDaoH2());
        PatientService patientService = new PatientService(new PatienDaoH2());

        //CREAMOS LAS TABLAS DE LA DB
        DB.createTable();

        //INSTANCIAMOS OBJETOS DE DENTIST
        Dentist dentist1 = new Dentist(2234, "Mikel", "Iturriaga");
        Dentist dentist2 = new Dentist(2235, "Francis", "Balencia");
        Dentist dentist3 = new Dentist(2236, "Margot", "Gunfel");


        //CREAMOS LA VARIBALE DE FECHA Y HORA ACTUAL
        Date admissionDate = new Date();

        //INSTANCIAMOS LOS OBJETOS PATIENT
        Patient patient1 = new Patient(4455, "Alameda", "Smith", admissionDate);
        Patient patient2 = new Patient(4456, "Mary", "Snapchu", admissionDate);
        Patient patient3 = new Patient(4457, "Yves", "Gunfel", admissionDate);

        //PERSISTIMOS LOS OBJETOS EN LA TABLA DE DENTISTA (GUARDAMOS)
        dentistService.save(dentist1);
        dentistService.save(dentist2);
        dentistService.save(dentist3);

        //PERSISTIMOS LOS OBJETOS EN LA TABLA DE PACIENTES (GUARDAMOS)
        patientService.save(patient1);
        patientService.save(patient2);
        patientService.save(patient3);

        //CONSULATMOS UN ODONTOLOGO POR ID
        dentistService.findById(1);

        //CONSULATMOS UN PACIENTE POR ID
        patientService.findById(1);

        //ACTUALIZAMOS LOS DATOS DE UN ODONTOLOGO
        String updateNameDentist = "Morgel";
        dentist2.setName(updateNameDentist);
        dentistService.update(dentist2);
        LOGGER.info("The updated name is: " + dentist2.getName());

        //ACTUALIZAMOS LOS DATOS DE UN PACIENTE
        String updateNamePatient = "Stephen";
        patient1.setName(updateNamePatient);
        patientService.update(patient1);
        LOGGER.info("The updated name is: " + patient1.getName());

        //ELIMINAMOS UN REGISTRO DE LA TABLA DENTIST
        dentistService.delete(1);

         //ELIMINAMOS UN REGISTRO DE LA TABLA PATIENT
         patientService.delete(2);

        //CONSULTAMOS TODOS LOS REGISTROS DE LA TABLA DENTIST
        dentistService.findAll();

        //CONSULTAMOS TODOS LOS REGISTROS DE LA TABLA PATIENT
        patientService.findAll();
    }
}
