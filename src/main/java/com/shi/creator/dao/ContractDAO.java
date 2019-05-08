package com.shi.creator.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shi.creator.entity.Contract;
import com.shi.creator.util.SessionFactoryUtil;

public class ContractDAO {
    public Contract findById(long id) {
        return SessionFactoryUtil.getSessionFactory().openSession().get(Contract.class, id);
    }

    public void save(Contract contract) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(contract);
        tx1.commit();
        session.close();
    }

    public void update(Contract contract) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(contract);
        tx1.commit();
        session.close();
    }

    public void delete(Contract contract) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(contract);
        tx1.commit();
        session.close();
    }

    public Contract findContractByNumber(String number) {
        return SessionFactoryUtil.getSessionFactory().openSession()
        			.createNamedQuery(Contract.FIND_BY_NUMBER, Contract.class).getSingleResult();
    }

    public List<Contract> findAll() {
        return  SessionFactoryUtil.getSessionFactory().openSession()
        			.createNamedQuery(Contract.SELECT_ALL, Contract.class).getResultList();
    }
}
