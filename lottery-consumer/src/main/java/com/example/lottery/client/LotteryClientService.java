package com.example.lottery.client;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@ConditionalOnProperty(name = "clientSideLoadBalancing", havingValue = "custom")
public class LotteryClientService {
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> lotteryServiceInstances;
	private final AtomicInteger counter = new AtomicInteger(0);
	
	public LotteryClientService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	public void getLotteryInstanceListFromEureka() {
		lotteryServiceInstances = discoveryClient.getInstances("lottery");
	}
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var restTemplate = new RestTemplate();
		var instance = lotteryServiceInstances.get(
				counter.getAndIncrement() % lotteryServiceInstances.size() );
		String url = "http://%s:%d/api/v1/numbers?column=1".formatted(instance.getHost(),instance.getPort());
		System.out.println("Sending request to %s.".formatted(url));
		var response = restTemplate.getForEntity(url, String.class);
		System.out.println(response.getBody());
	}
}
