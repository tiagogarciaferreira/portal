package br.srv.portal.configuration;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import br.srv.portal.model.Email;

public class JavaMail {

	private Session session = null;
	private String REMETENTE;
	private String SENHA;
	private String ASSINATURA = "3E Gestão de Pessoas";

	public JavaMail(String REMETENTE, String SENHA) {
		this.REMETENTE = REMETENTE;
		this.SENHA = SENHA;
		init();
	} 

	private void init() {
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(REMETENTE, SENHA);
			}
		});
	}

	public void send(Email email) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.setFrom(new InternetAddress(REMETENTE, ASSINATURA, "UTF-8"));
			message.setSubject(email.getAssunto(), "UTF-8");
			message.setContent(email.getMensagem(), "text/html;charset=UTF-8");
			Address[] addresses = InternetAddress.parse(email.getDestinatario());
			message.setRecipients(Message.RecipientType.TO, addresses);
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
