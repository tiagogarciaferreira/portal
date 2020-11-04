package br.srv.portal.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import br.srv.portal.configuration.JavaMail;
import br.srv.portal.model.Email;

@Service
@EnableAsync
public class ServiceEmail {

	@Value("${mail.remetente.email}")
	private String REMETENTE;
	@Value("${mail.remetente.senha}")
	private String SENHA;
	
	@Async
	public void enviar(Email email) {
		new JavaMail(REMETENTE, SENHA).send(email);
	}
	
}
