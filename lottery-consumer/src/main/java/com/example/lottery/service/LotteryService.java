package com.example.lottery.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lottery.dto.LotteryDTO;

@FeignClient(name = "lottery",fallback = FallbackLotteryService.class)
public interface LotteryService {
	@GetMapping(value="/api/v1/numbers",params = "column")
	public List<LotteryDTO> getNumbers(@RequestParam int column);
}
