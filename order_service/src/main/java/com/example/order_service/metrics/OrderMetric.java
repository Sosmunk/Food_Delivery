package com.example.order_service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderMetric {
    private final MeterRegistry registry;
    private final Map<LocalDate, Counter> dailyRequests;

    @PostConstruct
    public void init() {
        LocalDate date = LocalDate.now();
        dailyRequests.put(date, createCounter(date));
    }

    private Counter createCounter(LocalDate date) {
        return Counter.builder("order.requests")
                .tag("date", date.toString())
                .description("Daily requests")
                .register(registry);
    }

    public void incrementOrderCounter() {
        LocalDate date = LocalDate.now();
        dailyRequests.computeIfAbsent(date, this::createCounter);
        dailyRequests.get(date).increment();
    }
}
