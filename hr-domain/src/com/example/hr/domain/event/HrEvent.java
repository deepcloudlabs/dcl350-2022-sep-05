package com.example.hr.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public abstract class HrEvent {
	private static final AtomicLong sequence = new AtomicLong();
	private final String eventId = UUID.randomUUID().toString();
	private final long eventSequenceId = sequence.incrementAndGet();
	private final ZonedDateTime timestamp = ZonedDateTime.now();

	public String getEventId() {
		return eventId;
	}

	public long getEventSequenceId() {
		return eventSequenceId;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

}
