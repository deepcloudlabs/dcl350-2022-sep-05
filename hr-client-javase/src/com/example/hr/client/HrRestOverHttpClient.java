package com.example.hr.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class HrRestOverHttpClient {
	private static final String HR_REST_API_URL = "http://localhost:4200/hr/api/v1/employees/75405598520";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(HR_REST_API_URL)).build();
		var response = client.send(request, BodyHandlers.ofString()).body();
		System.out.println(response);
	}

}
