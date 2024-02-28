package DAO;

import fa.training.entities.Candidate;

import java.util.List;

public interface CandidateDao {
    Candidate create(Candidate candidate);

    List<Candidate> readALl();

    Candidate readById(Integer id);

    void update(Candidate candidate);

    void delete(Integer id);
}
