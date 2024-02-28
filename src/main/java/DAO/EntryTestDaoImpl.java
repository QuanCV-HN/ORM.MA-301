package DAO;

import Utils.HibernateUtils;
import fa.training.entities.EntryTest;
import fa.training.entities.Interview;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EntryTestDaoImpl implements EntryTestDao{
    @Override
    public EntryTest create(EntryTest entryTest) {
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            session.save(entryTest);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return entryTest;
    }

    @Override
    public List<EntryTest> readALl() {
        List<EntryTest> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<EntryTest> query = session.createQuery("FROM EntryTest ",EntryTest.class);
            list = query.getResultList();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public EntryTest readById(Integer id) {
        EntryTest entryTest = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<EntryTest> query = session.createQuery("FROM EntryTest WHERE id = :id",EntryTest.class);
            query.setParameter("id", id);
            entryTest = query.getSingleResult();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return entryTest;
    }

    @Override
    public void update(EntryTest entryTest) {
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            EntryTest entryTestDB = session.get(EntryTest.class, entryTest.getTestId());
            entryTestDB.setTechnicalValuator(entryTest.getTechnicalValuator());
            entryTestDB.setLanguageValuator(entryTest.getLanguageValuator());
            session.update(entryTestDB);

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Integer row = 0;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query query = session.createQuery("DELETE EntryTest  WHERE id = :id");
            query.setParameter("id", id);
            row = query.executeUpdate();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }
}
