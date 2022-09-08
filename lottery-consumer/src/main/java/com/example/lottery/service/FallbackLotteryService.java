package com.example.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lottery.dto.LotteryDTO;

@Service
public class FallbackLotteryService implements LotteryService {

	@Override
	public List<LotteryDTO> getNumbers(int column) {
		return List.of(new LotteryDTO(List.of(1,2,3,4,5,6)));
	}

}
