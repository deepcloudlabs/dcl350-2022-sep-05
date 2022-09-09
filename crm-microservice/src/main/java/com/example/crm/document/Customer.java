package com.example.crm.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="customers")
@Data
public class Customer {
	@Id
	private String customerId;
	private String fullname;
	private String email;
	private String sms;
}
