package com.sandbox.transactions.core.services;

import com.sandbox.transactions.core.entities.Singer;
import com.sandbox.transactions.core.repositories.SingerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service("singerService")
@Transactional
public class SingerServiceImp implements SingerService {
    private final SingerRepository singerRepository;

//    @Autowired
//    public SingerServiceImp(SingerRepository singerRepository) {
//        this.singerRepository = singerRepository;
//    }

//    @Autowired
//    private TransactionTemplate transactionTemplate;
//
//    @PersistenceContext
//    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll () {
        return new ArrayList(Arrays.asList(singerRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public void save(Singer singer) {
        singerRepository.save(singer);
        logger.debug("Singer was added");
    }

    @Override
    public long countAll() {
        return singerRepository.count();
    }

//    @Override
//    @Transactional(propagation = Propagation.NEVER)
//    public long countAllSingers() {
//        return singerRepository.countAllSingers();
//    }

//    @Override
//    public long countAllSingers() {
//        return transactionTemplate.execute(transactionStatus -> em.createNamedQuery(Singer.COUNT_ALL,
//                Long.class).getSingleResult());
//    }

//    public long countAllSingers() {
//        return transactionTemplate.execute(new TransactionCallback<Long>() {
//            public Long doInTransaction(TransactionStatus transactionStatus) {
//                return em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult();
//            }
//        }
//    }
}
