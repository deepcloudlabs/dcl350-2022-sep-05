package com.example.hr.infra;

import com.example.hexagonal.DrivenSide;

@DrivenSide
public interface EventPublisher<T> {

	void publish(T event);

}
