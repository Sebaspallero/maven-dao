import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dao.DB;
import dao.impl.DentistDaoH2;
import model.Dentist;
import service.DentistService;

public class DentistServiceTest {
    
    DentistService dentistService = new DentistService(new DentistDaoH2());

    @Test
    void save(){
        DB.createTable();
        Dentist dentist = new Dentist();
        dentist.setRegistration(2233);
        dentist.setName("Teston");
        dentist.setLastName("Testington");

        dentistService.save(dentist);

        assertNotNull(dentist.getId());
    }

    @Test
    void findById(){
        Dentist dentist = dentistService.findById(1);
        System.out.println(dentist.getName());
        assertNotNull(dentist);
    }

    @Test
    void update(){
        Dentist dentist = dentistService.findById(1);

        String updatedName = "Testerton";
        dentist.setName(updatedName);
        dentistService.update(dentist);

        Dentist updatedDentist = dentistService.findById(1);

        assertEquals(true, updatedDentist.getName().equals(updatedName));
    }

    @Test
    void delete(){
        Dentist dentistDeleted = dentistService.findById(1);
        assertNull(dentistDeleted);
    }

    @Test
    void findAll(){
        List<Dentist> dentistList = dentistService.findAll();
        assertTrue(dentistList.size() > 0);
    }
}
