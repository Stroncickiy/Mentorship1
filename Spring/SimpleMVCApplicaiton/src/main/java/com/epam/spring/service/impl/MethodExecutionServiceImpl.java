package com.epam.spring.service.impl;


import com.epam.spring.dao.MethodExecutionDAO;
import com.epam.spring.model.MethodExecutionRecord;
import com.epam.spring.service.MethodExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodExecutionServiceImpl implements MethodExecutionService {

    @Autowired
    private MethodExecutionDAO dao;

    @Async
    @Override
    public void register(MethodExecutionRecord record) {
        dao.add(record);
    }

    @Override
    public List<MethodExecutionRecord> getAll() {
        return dao.getAll();
    }

    @Override
    public List<MethodExecutionRecord> getLongRunningMethods() {
        return dao.getLongRunningMethods();
    }


}
