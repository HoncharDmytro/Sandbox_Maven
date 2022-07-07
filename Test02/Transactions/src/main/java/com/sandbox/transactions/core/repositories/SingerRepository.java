package com.sandbox.transactions.core.repositories;

import com.sandbox.transactions.core.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("singerRepository")
public interface SingerRepository extends CrudRepository<Singer, Long> {
//    @Query("select count(s) from Singer s")
//    Long countAllSingers();
}
