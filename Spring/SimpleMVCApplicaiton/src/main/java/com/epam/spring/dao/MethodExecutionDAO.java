package com.epam.spring.dao;

import com.epam.spring.model.MethodExecutionRecord;

import java.util.List;


public interface MethodExecutionDAO extends CommonDAO<MethodExecutionRecord> {
    List<MethodExecutionRecord> getLongRunningMethods();
}
