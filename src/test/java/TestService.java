import DAO.CandidateDao;
import DAO.CandidateDaoImpl;
import DAO.ServiceDAO;
import DAO.ServiceDAOImpl;
import fa.training.entities.Candidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class TestService {
   ServiceDAO serviceDAO = new ServiceDAOImpl();

    //a) Find all of the candidate that has skill is 'Java' and skill level is 3.
    @Test
    public void finda(){
        String skill = "Java";
        Integer level = 3;
        List<Candidate>  list = serviceDAO.findA(skill,level);
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }

    //b) Find all of the candidate that has foreign language is 'Japanese' and skill is 'Python/ML'.
    @Test
    public void findb(){
        String skill = "Python/ML";
       String  foreignLanguage = "Japanese";
        List<Candidate>  list = serviceDAO.findB(skill, foreignLanguage);
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }

    //c) Find all of the candidate by skill and entry test result (that has skill is ‘Java’ and pass entry test
    //on '1-Jan-2023').
    @Test
    public void find_c(){
        Date date = new Date(122,9,15);
        List<Candidate>  list = serviceDAO.findC(date);
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }

    //d) Find all of the candidate that failed interview on '15-Oct-2022'.
    @Test
    public void find_d(){
        Date date = new Date(122,9,15);
        List<Candidate>  list = serviceDAO.findD(date);
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }

    //e) Update remark is inactive for candidates who do not have either phone, email and cv.
    @Test
    public void updateRemark(){
       String remark = "abc";
     Assertions.assertTrue(   serviceDAO.updateRemark(remark));

    }

    //f) Create a method to proceed paging operation for Candidate use Hibernate Criteria Query
    //Language.
    @Test
    public void getCandidatesByPage(){
        int pageNumber = 1;
        int pageSize = 3;
        List<Candidate>  list = serviceDAO.getCandidatesByPage(pageNumber, pageSize);
        list.forEach(l ->{
            System.out.println(l);
        });
        Assertions.assertNotNull(list);
    }
}
