package DAO;

import Utils.HibernateUtils;
import fa.training.entities.Candidate;
import fa.training.entities.Interview;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class InterviewDaoImpl implements InterviewDao
{
    @Override
    public Interview create(Interview interview) {
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            session.save(interview);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return interview;
    }

    @Override
    public List<Interview> readALl() {
        List<Interview> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<Interview> query = session.createQuery("FROM Interview ",Interview.class);
            list = query.getResultList();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Interview readById(Integer id) {
        Interview interview = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<Interview> query = session.createQuery("FROM Interview WHERE id = :id",Interview.class);
            query.setParameter("id", id);
            interview = query.getSingleResult();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return interview;
    }

    @Override
    public void update(Interview interview) {

        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

           Interview interviewDB = session.get(Interview.class, interview.getInterviewId());
           interviewDB.setComments(interview.getComments());
           interviewDB.setInterviewResult(interview.getInterviewResult());
           session.update(interviewDB);

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

            Query query = session.createQuery("DELETE Interview  WHERE id = :id");
            query.setParameter("id", id);
            row = query.executeUpdate();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }
}
