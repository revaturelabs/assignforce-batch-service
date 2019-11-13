package com.revature.assignforce.service;

import com.revature.assignforce.beans.Batch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class RpBatchService {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Batch batch) {
        sessionFactory.getCurrentSession().save(batch);
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
