package com.skblab.project.delivery_service.config;

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
    public Queue inDeliveryOrderQueue() {
        log.info("In delivery order created");
        return new Queue("in_delivery_order_queue", true);
    }

    @Bean
    public Queue deliveredOrderQueue() {
        log.info("Delivered order created");
        return new Queue("delivered_order_queue", true);
    }

    @Bean
    public Binding inDeliveryOrderBinding(DirectExchange orderDirectExchange, Queue inDeliveryOrderQueue) {
        return BindingBuilder.bind(inDeliveryOrderQueue).to(orderDirectExchange).with("order.in_delivery");
    }

    @Bean
    public Binding deliveredOrderBinding(DirectExchange orderDirectExchange, Queue deliveredOrderQueue) {
        return BindingBuilder.bind(deliveredOrderQueue).to(orderDirectExchange).with("order.delivered");
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
