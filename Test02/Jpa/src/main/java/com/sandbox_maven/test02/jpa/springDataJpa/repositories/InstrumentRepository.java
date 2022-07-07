package com.sandbox_maven.test02.jpa.springDataJpa.repositories;

import com.sandbox_maven.test02.jpa.crud.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("instrumentRepository")
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

}