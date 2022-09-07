package com.example.hr.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class WebsocketHrEventConsumerService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	
	public WebsocketHrEventConsumerService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connectToWebSocketServer() {
		webSocketClient.doHandshake(this, "ws://localhost:4200/hr/api/v1/events");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the hr websocket endpoint.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("[WebsocketHrEventConsumerService] has received an hr-event: %s.".formatted(message.getPayload().toString()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error in websocket at session %s: %s.".formatted(session.getId(),e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the hr websocket endpoint.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
