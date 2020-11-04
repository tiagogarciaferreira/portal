package br.srv.portal.encrypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

import br.srv.portal.constant.E_Rsa;

public class Rsa {

	private KeyFactory keyFactory;
	private Cipher cipher;

	public Rsa() {
		/* newChave(); */
	}

	private void newChave() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(E_Rsa.ALGORITHM.get());
			keyPairGenerator.initialize(Integer.parseInt(E_Rsa.TAMANHO_CHAVE.get()));
			KeyPair keypair = keyPairGenerator.generateKeyPair();
			/*System.out.println("Chave Publica: " + getStringPublicKey(keypair));
			  System.out.println("Chave Privada: " + getStringPrivateKey(keypair));*/
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private String getStringPublicKey(KeyPair keypair) {
		return Base64.getEncoder().encodeToString(keypair.getPublic().getEncoded());
	}

	private String getStringPrivateKey(KeyPair keypair) {
		return Base64.getEncoder().encodeToString(keypair.getPrivate().getEncoded());
	}

	private PublicKey getPublicKey(String stringChave) {

		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(stringChave));
			keyFactory = KeyFactory.getInstance(E_Rsa.ALGORITHM.get());
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PrivateKey getPrivateKey(String stringChave) {

		try {
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(stringChave));
			keyFactory = KeyFactory.getInstance(E_Rsa.ALGORITHM.get());
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String crypt(String dados) {
		try {
			cipher = Cipher.getInstance(E_Rsa.ALGORITHM.get());
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(E_Rsa.CHAVE_PUBLICA.get()));
			byte[] textoCriptografado = cipher.doFinal(dados.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(textoCriptografado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String descrypt(String dados) {
		try {
			cipher = Cipher.getInstance(E_Rsa.ALGORITHM.get());
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(E_Rsa.CHAVE_PRIVADA.get()));
			byte[] textoDescriptografado = cipher.doFinal(Base64.getDecoder().decode(dados));
			return new String(textoDescriptografado);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
