package DAO;

import Utils.HibernateUtils;
import fa.training.entities.Candidate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CandidateDaoImpl implements CandidateDao{
    @Override
    public Candidate create(Candidate candidate) {
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

//            String hql = "INSERT INTO Candidate (fullName, dateOfBirth, gender, graduationYear, phone, email, skill, foreignLanguage, level, cv, allocationStatus, remark) " +
//                    "VALUES (:fullName, :dateOfBirth, :gender, :graduationYear, :phone, :email, :skill, :foreignLanguage, :level, :cv, :allocationStatus, :remark)";
//
//            Query<Candidate> query = session.createQuery(hql);
//            query.setParameter("fullName", candidate.getFullName());
//            query.setParameter("dateOfBirth", candidate.getDateOfBirth());
//            query.setParameter("gender", candidate.getGender());
//            query.setParameter("graduationYear", candidate.getGraduationYear());
//            query.setParameter("phone", candidate.getPhone());
//            query.setParameter("email", candidate.getEmail());
//            query.setParameter("skill", candidate.getSkill());
//            query.setParameter("foreignLanguage", candidate.getForeignLanguage());
//            query.setParameter("level", candidate.getLevel());
//            query.setParameter("cv", candidate.getCv());
//            query.setParameter("allocationStatus", candidate.getAllocationStatus());
//            query.setParameter("remark", candidate.getRemark());
//
//            int numRowsInserted = query.executeUpdate();

            session.save(candidate);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return candidate;
    }

    @Override
    public List<Candidate> readALl() {
        List<Candidate> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<Candidate> query = session.createQuery("FROM Candidate c",Candidate.class);
            list = query.getResultList();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Candidate readById(Integer id) {
        Candidate candidate = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<Candidate> query = session.createQuery("FROM Candidate WHERE id = :id",Candidate.class);
            query.setParameter("id", id);
            candidate = query.getSingleResult();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return candidate;

    }

    @Override
    public void update(Candidate candidate) {
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<Candidate> query = session.createQuery("UPDATE Candidate SET phone = :phone WHERE id = :id");
            query.setParameter("phone",candidate.getPhone());
            query.setParameter("id", candidate.getCandidateId());

            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query query1 = session.createQuery("DELETE FROM EntryTest WHERE candidateId = :id");
            query1.setParameter("id", id);
            query1.executeUpdate();

            Query query2 = session.createQuery("DELETE Interview  WHERE candidateId = :id");
            query2.setParameter("id", id);
            query2.executeUpdate();

            Query query = session.createQuery("DELETE Candidate  WHERE id = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
