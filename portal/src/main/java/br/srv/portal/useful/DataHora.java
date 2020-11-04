package br.srv.portal.useful;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataHora {
	
	private static final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
	
	public static LocalDateTime getAtual() {
		return LocalDateTime.now(zoneId);
	}
	
	public static String atualFormatada() {
		return getAtual().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	

}
