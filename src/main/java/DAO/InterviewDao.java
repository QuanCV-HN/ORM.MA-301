package DAO;

import fa.training.entities.Candidate;
import fa.training.entities.Interview;

import java.util.List;

public interface InterviewDao {
    Interview create(Interview interview);

    List<Interview> readALl();

    Interview readById(Integer id);

    void update(Interview interview);

    Boolean delete(Integer id);
}
