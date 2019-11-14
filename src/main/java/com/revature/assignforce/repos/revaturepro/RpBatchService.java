package com.revature.assignforce.repos.revaturepro;

import com.revature.assignforce.beans.Batch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
@PersistenceContext
@Service
public class RpBatchService {

    @Autowired
    private SessionFactory sessionFactory;


    public void save(Batch batch) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(batch);
        tx.commit();
        session.clear();
    }

    public List<Batch> findAll() {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Batch> criteria = builder.createQuery(Batch.class);
        criteria.from(Batch.class);

        List<Batch> data = session.createQuery(criteria).getResultList();
        return data;
    }

}
