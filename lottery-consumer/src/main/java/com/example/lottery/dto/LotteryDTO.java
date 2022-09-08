package com.example.lottery.dto;

import java.util.List;

public class LotteryDTO {
	private List<Integer> lotteryNumbers;

	public LotteryDTO() {
	}

	public LotteryDTO(List<Integer> lotteryNumbers) {
		this.lotteryNumbers = lotteryNumbers;
	}

	public List<Integer> getLotteryNumbers() {
		return lotteryNumbers;
	}

	public void setLotteryNumbers(List<Integer> lotteryNumbers) {
		this.lotteryNumbers = lotteryNumbers;
	}

	@Override
	public String toString() {
		return "LotteryDTO [lotteryNumbers=" + lotteryNumbers + "]";
	}

}
