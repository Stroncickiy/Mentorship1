package com.epam.spring.events;

import com.epam.spring.model.MethodExecutionRecord;
import org.springframework.context.ApplicationEvent;

public class LongMethodRunningEvent extends ApplicationEvent {

    private MethodExecutionRecord executionRecord;

    public LongMethodRunningEvent(MethodExecutionRecord executionRecord, Object source) {
        super(source);
        this.executionRecord = executionRecord;
    }

    public MethodExecutionRecord getExecutionRecord() {
        return executionRecord;
    }

    public void setExecutionRecord(MethodExecutionRecord executionRecord) {
        this.executionRecord = executionRecord;
    }
}
