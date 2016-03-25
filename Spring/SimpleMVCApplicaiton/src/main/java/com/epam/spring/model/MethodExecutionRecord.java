package com.epam.spring.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class MethodExecutionRecord {
    private Long id;
    private String methodName;
    private Duration duration;
    private LocalDateTime executed;
    private boolean permittedDurationExceeded;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getExecuted() {
        return executed;
    }

    public void setExecuted(LocalDateTime executed) {
        this.executed = executed;
    }

    public boolean isPermittedDurationExceeded() {
        return permittedDurationExceeded;
    }

    public void setPermittedDurationExceeded(boolean permittedDurationExceeded) {
        this.permittedDurationExceeded = permittedDurationExceeded;
    }
}
