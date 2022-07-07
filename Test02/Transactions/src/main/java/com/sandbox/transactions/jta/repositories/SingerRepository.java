package com.sandbox.transactions.jta.repositories;

import com.sandbox.transactions.jta.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("singerRepository")
public interface SingerRepository extends CrudRepository<Singer, Long> {
}
