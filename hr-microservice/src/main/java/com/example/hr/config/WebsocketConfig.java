package com.example.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.HrEventWebSocketService;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

	private final HrEventWebSocketService hrEventWebSocketService;
	
	public WebsocketConfig(HrEventWebSocketService hrEventWebSocketService) {
		this.hrEventWebSocketService = hrEventWebSocketService;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(hrEventWebSocketService, "/events").setAllowedOrigins("*");
	}
}