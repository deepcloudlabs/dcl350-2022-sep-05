package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.dto.LotteryDTO;
import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("/numbers")
@Validated
@CrossOrigin
public class LotteryRestController {

	private LotteryService lotteryService;

	@Value("${server.port}")
	private int serverPort;
	
	public LotteryRestController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@GetMapping(params = "column")
	public List<LotteryDTO> getLotteryNumbers(@RequestParam int column){
		System.out.println("New request has arrived for port %d".formatted(serverPort));
		return lotteryService.drawNumbers(column);
	}
}
