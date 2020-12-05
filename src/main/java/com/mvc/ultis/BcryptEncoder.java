package com.mvc.ultis;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoder extends BCryptPasswordEncoder {

	public String BcryptEncoder(String code) {
		BcryptEncoder bcryptEncoder = new BcryptEncoder();
		return bcryptEncoder.encode(code);

	}
}
