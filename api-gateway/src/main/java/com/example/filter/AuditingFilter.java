package com.example.filter;

import java.util.Date;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuditingFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		System.err.println("A new request to %s has arrived at %s".formatted(request.getPath(),new Date()));
		System.err.println("Query parameters are %s".formatted(request.getQueryParams()));
		request.getBody().subscribe(System.err::println);
		request.getHeaders().forEach((headerName,headerValue) -> System.err.println("%s: %s".formatted(headerName,headerValue)));
		return chain.filter(exchange);
	}

}
