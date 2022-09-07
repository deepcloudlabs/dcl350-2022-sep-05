package com.example.hr.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.TimeUnit;

public class HrRestOverHttpAsyncClient {
	private static final String HR_REST_API_URL = "http://localhost:4200/hr/api/v1/employees/75405598520";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(HR_REST_API_URL)).build();
		client.sendAsync(request, BodyHandlers.ofString())
		      .thenAcceptAsync(response -> System.err.println(response.body()));
		for (var i=0;i<100_000;++i) {
			if (i%1_000 == 0) {
				System.out.println("Processing precious data... %d".formatted(i));
				try {TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e) {}
			}
		}
	}

}
