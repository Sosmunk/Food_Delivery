package com.example.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

public class AMQPConfiguration {

    @Bean
    public DirectExchange orderDirectExchange() {
        return new DirectExchange("order_exchange");
    }

    @Bean
    public Queue paidOrderQueue() {
        return new Queue("paid_order_queue", true);
    }

    @Bean
    public Binding paidOrderBinding(DirectExchange orderDirectExchange, Queue paidOrderQueue) {
        return BindingBuilder.bind(paidOrderQueue).to(orderDirectExchange).with("order.paid");
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
