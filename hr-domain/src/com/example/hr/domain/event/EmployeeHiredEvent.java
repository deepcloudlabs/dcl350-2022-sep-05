package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends HrEvent {
	private final TcKimlikNo identity;

	public EmployeeHiredEvent(TcKimlikNo identity) {
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

}
