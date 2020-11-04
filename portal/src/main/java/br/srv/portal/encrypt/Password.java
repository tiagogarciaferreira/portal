package br.srv.portal.encrypt;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

	public String encoder(String senhaTxt) {
		return new BCryptPasswordEncoder().encode(senhaTxt);
	}

	public String gerar() {
		return RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(15, 20));
	}
	
	public String gerarEncode() {
		return encoder(gerar());
	}

}
