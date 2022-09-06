package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeFiredEvent extends HrEvent {
	private final TcKimlikNo identity;

	public EmployeeFiredEvent(TcKimlikNo identity) {
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}
}
