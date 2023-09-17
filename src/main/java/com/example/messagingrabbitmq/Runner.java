package com.example.messagingrabbitmq;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	FanoutExchange exchange;


	public Runner(RabbitTemplate rabbitTemplate, FanoutExchange exchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.exchange = exchange;
	}

	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.setExchange(exchange.getName());
		Scanner scanner = new Scanner(System.in);
		String message;
		while (true){
			message = scanner.nextLine();
			rabbitTemplate.convertAndSend(message);
		}
	}

}
