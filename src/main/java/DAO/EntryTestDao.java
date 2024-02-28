package DAO;

import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;

import java.util.List;

public interface EntryTestDao {
    EntryTest create(EntryTest entryTest);

    List<EntryTest> readALl();

    EntryTest readById(Integer id);

    void update(EntryTest entryTest);

    Boolean delete(Integer id);
}
