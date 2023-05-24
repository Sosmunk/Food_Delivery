package com.skblab.project.delivery_service;

import com.skblab.project.delivery_service.dto.CourierRequest;
import com.skblab.project.delivery_service.model.Courier;
import com.skblab.project.delivery_service.service.CourierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
public class DeliveryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeliveryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner createCouriers(CourierService courierService) {
		return (args) -> {
			CourierRequest courier1 = CourierRequest.builder()
				.name("Andrew")
				.surname("Fer")
				.phone("89089183022")
				.build();

			CourierRequest courier2 = CourierRequest.builder()
				.name("Andrew")
				.surname("Mesilov")
				.phone("89676377943")
				.build();

			CourierRequest courier3 = CourierRequest.builder()
				.name("Aboba")
				.surname("Abobin")
				.phone("89008324124")
				.build();

			courierService.createCourier(courier1);
			courierService.createCourier(courier2);
			courierService.createCourier(courier3);
		};
	}
}
