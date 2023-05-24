package com.skblab.project.account_service.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MetricScheduler {

    private final AtomicInteger gauge;
    private final Counter counter;
    private final Random random;

    public MetricScheduler(MeterRegistry meterRegistry) {
        gauge = meterRegistry.gauge("test_gauge", new AtomicInteger(0));
        counter = meterRegistry.counter("test_counter");
        random = new Random();
    }

    @Scheduled(fixedDelayString = "1000", initialDelayString = "0")
    public void schedulingTask() {
        gauge.set(random.nextInt(10));
        counter.increment();
    }
}
