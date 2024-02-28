import DAO.EntryTestDao;
import DAO.EntryTestDaoImpl;
import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;
import fa.training.entities.Interview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TestEntryTest {

    EntryTestDao entryTestDao = new EntryTestDaoImpl();
    @Test
    public void create(){
        EntryTest entryTest = new EntryTest();

        entryTest.setTime("10:00 AM");
        entryTest.setDate(new Date(122, 9, 15)); // Setting the date to January 1, 2020
        entryTest.setLanguageValuator("John Doe");
        entryTest.setLanguageResult(new BigDecimal("8.5"));
        entryTest.setTechnicalValuator("Jane Smith");
        entryTest.setTechnicalResult(new BigDecimal("9.2"));
        entryTest.setResult("pass");
        entryTest.setRemark("Good performance");
        entryTest.setCandidateId(1);
        entryTest.setEntryTestSkill("Java");

        // Create a Candidate object and set it in the EntryTest object
        Candidate candidate = new Candidate();
        candidate.setCandidateId(1);

        entryTest.setCandidate(candidate);

        Assertions.assertNotNull(entryTestDao.create(entryTest).getTestId());
    }

    @Test
    public void read_all(){
        List<EntryTest> list = entryTestDao.readALl();
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }

    @Test
    public void read_by_id(){
        Integer id = 2;
        EntryTest entryTest = entryTestDao.readById(id);
        System.out.println(entryTest);
        Assertions.assertNotNull(entryTest);
    }

    @Test
    public void update(){
        EntryTest entryTest = new EntryTest();
        entryTest.setTestId(2);
        entryTest.setLanguageValuator("aaaaaaaa");
        entryTest.setTechnicalValuator("bbbbbbb");

        entryTestDao.update(entryTest);

        EntryTest entryTestUp = entryTestDao.readById(entryTest.getTestId());
        Assertions.assertEquals(entryTestUp.getLanguageValuator(),entryTest.getLanguageValuator());
    }

    @Test
    public void delete(){
        Integer id = 2;
        Assertions.assertTrue( entryTestDao.delete(id));
    }
}
