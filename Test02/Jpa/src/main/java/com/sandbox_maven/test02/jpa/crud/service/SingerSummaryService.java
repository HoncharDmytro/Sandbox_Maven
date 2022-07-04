package com.sandbox_maven.test02.jpa.crud.service;

import com.sandbox_maven.test02.jpa.crud.modelView.SingerSummary;

import java.util.List;

public interface SingerSummaryService {
    List<SingerSummary> findAll();
}