package com.example.lottery.service;

import java.util.List;

import com.example.lottery.dto.LotteryDTO;

public interface LotteryService {

	LotteryDTO drawNumbers();

	List<LotteryDTO> drawNumbers(int column);

}
