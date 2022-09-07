package com.example.hr.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class HrRestOverWebSocketClient {
	private static final String HR_REST_API_URL = "ws://localhost:4200/hr/api/v1/events";

	public static void main(String[] args) throws IOException, InterruptedException {
		System.err.println("Application is running...");
		HttpClient.newHttpClient()
		          .newWebSocketBuilder()
		          .buildAsync(URI.create(HR_REST_API_URL), new HrEventListener());
		
		try {TimeUnit.SECONDS.sleep(60);}catch(Exception e) {}
		System.err.println("Application is completed.");
	}

}

class HrEventListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the hr websocket endpoint!");	
		Listener.super.onOpen(webSocket);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println("New event/message has arrived: %s.".formatted(data));	
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the hr websocket endpoint!");	
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("An error has occured in the websocket: %s.".formatted(error.getMessage()));	
		Listener.super.onError(webSocket, error);
	}
	
}