package com.cognizant.UserManagement.KafkaConsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@KafkaListener(topics = "kafka_topic0", groupId = "group0")
	public void consumer(String msg) {
		System.out.println("Movie Booking application tracking: " + msg);
	}

}
