package DAO;

import fa.training.entities.Candidate;


import java.util.Date;
import java.util.List;

public interface ServiceDAO {
    //a
    List<Candidate>  findA(String skill, Integer level);

    List<Candidate>  findB(String foreignLanguage, String skill);
    List<Candidate>  findC(Date date);

    List<Candidate>  findD(Date date);

    Boolean updateRemark (String remark);

    List<Candidate> getCandidatesByPage(int pageNumber, int pageSize);

}
