import DAO.InterviewDao;
import DAO.InterviewDaoImpl;
import fa.training.entities.Candidate;
import fa.training.entities.Interview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class TestInterview {
    InterviewDao interviewDao = new InterviewDaoImpl();

    @Test
    public void create(){
        Interview interview = new Interview();
        Candidate candidate = new Candidate();

        interview.setTime("10:00 AM");
        interview.setDate(new Date(122,9,15));
        interview.setInterviewer("John Doe");
        interview.setComments("Good performance");
        interview.setInterviewResult("Fail");
        interview.setRemark("Excellent communication skills");
        interview.setCandidateId(3);
        interview.setCandidate(candidate);

        candidate.setCandidateId(3);
        candidate.getInterviewList().add(interview);

        Assertions.assertNotNull( interviewDao.create(interview).getInterviewId());

    }

    @Test
    public void read_all(){
        List<Interview> list = interviewDao.readALl();
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }

    @Test
    public void read_by_id(){
        Integer id = 1;
        Interview interview = interviewDao.readById(id);
        System.out.println(interview);
        Assertions.assertNotNull(interview);
    }

    @Test
    public void update(){
        Interview interview = new Interview();
        interview.setInterviewId(1);
        interview.setInterviewResult("fail");
        interview.setComments("abcxyz");

        interviewDao.update(interview);

        Interview interviewUp = interviewDao.readById(interview.getInterviewId());
        Assertions.assertEquals(interviewUp.getComments(),interview.getComments());
    }

    @Test
    public void delete(){
        Integer id = 2;
       Assertions.assertTrue( interviewDao.delete(id));
    }
}
