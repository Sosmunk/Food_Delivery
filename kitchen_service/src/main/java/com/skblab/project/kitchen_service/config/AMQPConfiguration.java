package com.skblab.project.kitchen_service.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class AMQPConfiguration {

    @Bean
    public DirectExchange orderDirectExchange() {
        log.info("Order Direct Exchange created");
        return new DirectExchange("order_exchange");
    }

    @Bean
    public Queue isPreparingOrderQueue() {
        log.info("Is preparing order created");
        return new Queue("is_preparing_order_queue", true);
    }

    @Bean
    public Queue readyOrderQueue() {
        log.info("Ready order created");
        return new Queue("ready_order_queue", true);
    }

    @Bean
    public Binding isPreparingOrderBinding(DirectExchange orderDirectExchange, Queue isPreparingOrderQueue) {
        return BindingBuilder.bind(isPreparingOrderQueue).to(orderDirectExchange).with("order.is_preparing");
    }

    @Bean
    public Binding readyOrderBinding(DirectExchange orderDirectExchange, Queue readyOrderQueue) {
        return BindingBuilder.bind(readyOrderQueue).to(orderDirectExchange).with("order.ready");
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
