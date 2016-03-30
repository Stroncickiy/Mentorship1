package com.epam.spring.listener;

import com.epam.spring.events.LongMethodRunningEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LongMethodRunningEventListener implements ApplicationListener<LongMethodRunningEvent> {
    @Override
    public void onApplicationEvent(LongMethodRunningEvent longMethodRunningEvent) {
        System.out.println("--------- LONG METHOD ------------");
        System.out.println(longMethodRunningEvent.getExecutionRecord());
    }
}
