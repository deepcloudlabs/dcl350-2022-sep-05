package com.example.lottery.client;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;


@Service
public class LotteryFeignClientService {

	private final LotteryService lotteryService;
	
	public LotteryFeignClientService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		System.out.println(lotteryService.getNumbers(5));
	}
}
