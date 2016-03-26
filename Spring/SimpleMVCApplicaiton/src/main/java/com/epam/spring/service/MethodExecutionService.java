package com.epam.spring.service;

import com.epam.spring.model.MethodExecutionRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MethodExecutionService {

    void register(MethodExecutionRecord record);

    List<MethodExecutionRecord> getAll();

    List<MethodExecutionRecord> getLongRunningMethods();




}
