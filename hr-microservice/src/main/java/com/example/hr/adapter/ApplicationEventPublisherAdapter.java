package com.example.hr.adapter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.example.hr.infra.EventPublisher;

@Service
public class ApplicationEventPublisherAdapter implements EventPublisher<HrEvent> {
	private final ApplicationEventPublisher eventPublisher;
	
	public ApplicationEventPublisherAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publish(HrEvent event) {
		eventPublisher.publishEvent(event);
	}

}
