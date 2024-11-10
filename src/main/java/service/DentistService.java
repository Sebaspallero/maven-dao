package service;

import java.util.List;

import dao.IDao;
import model.Dentist;

public class DentistService {

    private IDao <Dentist> dentistIDao;

    //CONSTRUCTOR
    public DentistService(IDao<Dentist> dentistIDao) {
        this.dentistIDao = dentistIDao;
    }

    public Dentist save(Dentist dentist){
       return dentistIDao.save(dentist);
    }

    public Dentist findById(int id){
        return dentistIDao.findById(id);
    }

    public void update(Dentist dentist){
        dentistIDao.update(dentist);
    }

    public void delete(int id){
        dentistIDao.delete(id);
    }

    public List<Dentist> findAll(){
        return dentistIDao.findAll();
    }

    
}
