package com.example.card.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventKafkaListener {

	@KafkaListener(topics = "hr-events", groupId = "security-card")
	public void listenHrEvents(String event) {
		System.err.println("[HrEventKafkaListener] New HR Event has arrived: %s.".formatted(event));
	}
}
