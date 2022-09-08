package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.lottery.dto.LotteryDTO;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
@RefreshScope
public class StandardLotteryService implements LotteryService {
	private RandomNumberService randomNumberService;
	
	@Value("${lotteryMin}")
	private int lotteryMin;
	
	@Value("${lotteryMax}")
	private int lotteryMax;
	
	@Value("${lotterySize}")
	private int lotterySize;
	
	public StandardLotteryService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}

	@Override
	public LotteryDTO drawNumbers() {
		var numbers = IntStream.generate(() -> randomNumberService.generate(lotteryMin, lotteryMax))
		        .distinct()
		        .limit(lotterySize)
		        .sorted()
		        .boxed()
		        .toList();
		return new LotteryDTO(numbers);
	}

	@Override
	public List<LotteryDTO> drawNumbers(int column) {
		return IntStream.range(0, column).mapToObj(i -> drawNumbers()).toList();
	}

}
