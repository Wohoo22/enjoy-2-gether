package com.mvc.ultis;

import java.util.Random;

public class RandomVerifyCode {

	public int randomCode() {
		Random random = new Random();
		int i = random.nextInt(10000);
		return i;
	}
}
