package com.sandbox_maven.hibernate.dao;

import com.sandbox_maven.hibernate.entities.Instrument;

public interface InstrumentDao {
    void save(Instrument instrument);
}