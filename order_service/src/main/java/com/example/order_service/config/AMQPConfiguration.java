package com.example.order_service.config;

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
    public Queue toDeliveryOrderQueue() {
        log.info("To delivery order created");
        return new Queue("to_delivery_order_queue", true);
    }

    @Bean
    public Queue toKitchenOrderQueue() {
        log.info("To kitchen order created");
        return new Queue("to_kitchen_order_queue", true);
    }

    @Bean
    public Binding toKitchenOrderBinding(DirectExchange orderDirectExchange, Queue toKitchenOrderQueue) {
        return BindingBuilder.bind(toKitchenOrderQueue).to(orderDirectExchange).with("order.to_kitchen");
    }

    @Bean
    public Binding inDeliveryOrderBinding(DirectExchange orderDirectExchange, Queue inDeliveryOrderQueue) {
        return BindingBuilder.bind(inDeliveryOrderQueue).to(orderDirectExchange).with("order.in_delivery");
    }

    @Bean
    public Binding toDeliveryOrderBinding(DirectExchange orderDirectExchange, Queue toDeliveryOrderQueue) {
        return BindingBuilder.bind(toDeliveryOrderQueue).to(orderDirectExchange).with("order.to_delivery");
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
