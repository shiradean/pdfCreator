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

    public Contract findContractById(int id) {
        return SessionFactoryUtil.getSessionFactory().openSession().get(Contract.class, id);
    }

    public List<Contract> findAll() {
        List<Contract> contracts = (List<Contract>)  SessionFactoryUtil.getSessionFactory().openSession().createQuery("From Contract").list();
        return contracts;
    }
}
