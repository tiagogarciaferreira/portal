package br.srv.portal.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import br.srv.portal.services.ServiceNewsletter;

@Service
@EnableScheduling
public class TarefasNewletter {

	private static final String TIME_ZONE = "America/Sao_Paulo";
	@Autowired
	private ServiceNewsletter serviceNewsletter;
	
	
	@Scheduled(cron = "${task.time.cron}", zone = TIME_ZONE) 
	public void envioNewsletterDiario() {
		serviceNewsletter.emailNovaNewsletter();
	}
	
}
