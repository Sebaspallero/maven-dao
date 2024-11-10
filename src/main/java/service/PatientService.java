package service;

import java.util.List;

import dao.IDao;
import model.Patient;

public class PatientService {
     private IDao <Patient> patientIDao;

    //CONSTRUCTOR
    public PatientService(IDao<Patient> patientIDao) {
        this.patientIDao = patientIDao;
    }

    public Patient save(Patient Patient){
       return patientIDao.save(Patient);
    }

    public Patient findById(int id){
        return patientIDao.findById(id);
    }

    public void update(Patient Patient){
        patientIDao.update(Patient);
    }

    public void delete(int id){
        patientIDao.delete(id);
    }

    public List<Patient> findAll(){
        return patientIDao.findAll();
    }
}
