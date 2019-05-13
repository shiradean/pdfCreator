package com.shi.creator.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shi.creator.entity.Holder;
import com.shi.creator.util.SessionFactoryUtil;

public class HolderDAO {

    public Holder findById(long id) {
        return SessionFactoryUtil.getSessionFactory().openSession().get(Holder.class, id);
    }

    public void save(Holder holder) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(holder);
        tx1.commit();
        session.close();
    }

    public void update(Holder holder) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(holder);
        tx1.commit();
        session.close();
    }

    public void delete(Holder holder) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(holder);
        tx1.commit();
        session.close();
    }

    public Holder findHolderByEmail(String email) {
    	TypedQuery<Holder> query = SessionFactoryUtil.getSessionFactory().openSession()
    			.createNamedQuery(Holder.FIND_BY_EMAIL, Holder.class);
    	query.setParameter(Holder.FIND_BY_EMAIL, email);
        return query.getSingleResult();
    }

    public List<Holder> findAll() {
        return SessionFactoryUtil.getSessionFactory().openSession()
        			.createNamedQuery(Holder.SELECT_ALL, Holder.class).getResultList();
    }
}
