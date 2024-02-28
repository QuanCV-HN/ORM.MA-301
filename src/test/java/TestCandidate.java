import DAO.CandidateDao;
import DAO.CandidateDaoImpl;
import fa.training.entities.Candidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class TestCandidate {

    CandidateDao candidateDao = new CandidateDaoImpl();

    @Test
    public void create(){
        Candidate candidate = new Candidate();
        candidate.setFullName("John Doe");
        candidate.setDateOfBirth(new Date(122,9,15));

        candidate.setGender(1);
        candidate.setGraduationYear(new Date());
        candidate.setPhone("0000003");
        candidate.setEmail("uytr3@gmail.com");
        candidate.setSkill("Java");
        candidate.setForeignLanguage("Japanese");
        candidate.setLevel(3);
        candidate.setCv("cv");
        candidate.setAllocationStatus(0);
        candidate.setRemark("Some remark");
        candidate.setEntryTestList(null);
        candidate.setInterviewList(null);

        Assertions.assertNotNull(  candidateDao.create(candidate).getCandidateId());
    }

    @Test
    public void read_all(){
        List<Candidate> list = candidateDao.readALl();
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }

    @Test
    public void read_by_id(){
        Integer id = 1;
       Candidate candidate = candidateDao.readById(id);
        System.out.println(candidate);
        Assertions.assertNotNull(candidate);
    }

    @Test
    public void update(){
      Candidate candidate = new Candidate();
      candidate.setCandidateId(1);
      candidate.setPhone("19001009");

      candidateDao.update(candidate);

        Candidate candidateUp = candidateDao.readById(candidate.getCandidateId());
      Assertions.assertEquals(candidate.getPhone(), candidateUp.getPhone());
    }

    @Test
    public void delete(){
       Integer id = 1;

        candidateDao.delete(id);
    }
}
