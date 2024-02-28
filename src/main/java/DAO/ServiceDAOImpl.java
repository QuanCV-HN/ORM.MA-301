package DAO;

import Utils.HibernateUtils;
import fa.training.entities.Candidate;
import fa.training.entities.Interview;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO{
    @Override
    public List<Candidate> findA(String skill, Integer level) {

        List<Candidate> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<Candidate> query = session.createQuery("FROM Candidate WHERE skill LIKE :skill AND level = :level",Candidate.class);
            query.setParameter("skill", "%"+ skill +"%");
            query.setParameter("level", level);
            list = query.getResultList();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Candidate> findB(String skill,String foreignLanguage) {
        List<Candidate> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            Query<Candidate> query = session.createQuery("FROM Candidate WHERE skill LIKE :skill AND foreignLanguage LIKE :foreignLanguage",Candidate.class);
            query.setParameter("skill", "%"+ skill +"%");
            query.setParameter("foreignLanguage", "%"+ foreignLanguage +"%");

            list = query.getResultList();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Candidate> findC(Date date) {
        List<Candidate> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            session.beginTransaction();

            String hqlQuery = "SELECT C " +
                    "FROM Candidate C " +
                    "JOIN C.entryTestList E " +
                    "WHERE C.skill LIKE '%Java%' " +
                    "AND E.result = 'Pass' " +
                    "AND E.date = :date";
            Query<Candidate> query = session.createQuery(hqlQuery, Candidate.class);
            query.setParameter("date", date);
            list = query.list();

//            list = query.getResultList();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Candidate> findD(Date date) {
        List<Candidate> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Candidate> criteriaQuery = builder.createQuery(Candidate.class);
        Root<Candidate> canRoot = criteriaQuery.from(Candidate.class);
            Join<Candidate, Interview> interviewJoin = canRoot.join("interviewList");

            criteriaQuery.select(canRoot);
            criteriaQuery.where(
                    builder.and(
                            builder.equal(interviewJoin.get("interviewResult"), "Fail"),
                            builder.equal(interviewJoin.get("date"),date )
                    )
            );
            Query<Candidate> query = session.createQuery(criteriaQuery);
            list = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Boolean updateRemark(String remark) {
        int row = 0 ;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Candidate> updateCriteria = builder.createCriteriaUpdate(Candidate.class);
            Root<Candidate> root = updateCriteria.from(Candidate.class);

            updateCriteria.set(root.get("remark"), remark);
            updateCriteria.where(builder.and(
                    builder.isNull(root.get("phone")),
                    builder.isNull(root.get("email")),
                    builder.isNull(root.get("cv"))
                    ));

        row =  session.createQuery(updateCriteria).executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row >0;
    }

    @Override
    public List<Candidate> getCandidatesByPage(int pageNumber, int pageSize) {
        List<Candidate> list = null;
        try (
                Session session = HibernateUtils.getSessionFactory().openSession();
        ){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteriaQuery = builder.createQuery(Candidate.class);
            Root<Candidate> canRoot = criteriaQuery.from(Candidate.class);

            criteriaQuery.select(canRoot);

            int firstResult = (pageNumber - 1) * pageSize;
            int maxResults = pageSize;

            list = session.createQuery(criteriaQuery)
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResults)
                    .list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

