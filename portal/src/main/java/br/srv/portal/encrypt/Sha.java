package br.srv.portal.encrypt;

import org.apache.commons.codec.digest.DigestUtils;

public class Sha {

	public static String getSha(Long identificador) {
		String cifrar = DigestUtils.md5Hex(String.valueOf(identificador));
		return DigestUtils.sha3_384Hex(cifrar.getBytes());
	}
}
