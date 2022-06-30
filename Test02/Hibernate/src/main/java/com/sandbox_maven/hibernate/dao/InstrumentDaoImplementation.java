package com.sandbox_maven.hibernate.dao;

import com.sandbox_maven.hibernate.entities.Instrument;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImplementation implements InstrumentDao {

    private static final Logger logger = LoggerFactory.getLogger(InstrumentDaoImplementation.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Instrument instrument) {
        sessionFactory.getCurrentSession().persist(instrument);
        logger.info("Instrument saved with id: " + instrument.getInstrumentId());
    }
}