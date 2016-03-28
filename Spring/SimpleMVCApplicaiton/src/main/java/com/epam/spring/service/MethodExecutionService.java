package com.epam.spring.service;

import com.epam.spring.model.MethodExecutionRecord;

import java.util.List;


public interface MethodExecutionService {

    void register(MethodExecutionRecord record);

    List<MethodExecutionRecord> getAll();

    List<MethodExecutionRecord> getLongRunningMethods();


}
