package com.example.order_service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Map;

/**
 * Компонент метрик для заказов
 */
@Component
@RequiredArgsConstructor
public class OrderMetric {
    private final MeterRegistry registry;

    /**
     * Метрика описывающая, сколько было сделано заказов за определенный день
     */
    private final Map<LocalDate, Counter> dailyRequests;

    @PostConstruct
    public void init() {
        LocalDate date = LocalDate.now();
        dailyRequests.put(date, createDailyOrderCounter(date));
    }

    /**
     * Метод создающий счетчик заказов за определенный день
     * @param date дата
     * @return micrometer счётчик {@link Counter}
     */
    private Counter createDailyOrderCounter(LocalDate date) {
        return Counter.builder("order.requests")
                .tag("date", date.toString())
                .description("Daily requests")
                .register(registry);
    }

    /**
     * Метод увеличивающий счетчик заказов за сегодняшний день
     */
    public void incrementDailyOrderCounter() {
        LocalDate date = LocalDate.now();
        dailyRequests.computeIfAbsent(date, this::createDailyOrderCounter);
        dailyRequests.get(date).increment();
    }
}
