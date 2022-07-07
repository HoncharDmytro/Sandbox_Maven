package com.sandbox_maven.test02.jpa.crud.services;

import com.sandbox_maven.test02.jpa.crud.modelView.SingerSummary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SingerSummary> findAll() {
        return em.createQuery(
                "select new com.sandbox_maven.test02.jpa.crud.modelView.SingerSummary("
                        + "s.firstName, s.lastName, a.title) from Singer s "
                        + "left join s.albums a "
                        + "where a.releaseDate=(select max(a2.releaseDate) from Album a2 "
                        + "where a2.singer.id = s.id)", SingerSummary.class).getResultList();
    }
}