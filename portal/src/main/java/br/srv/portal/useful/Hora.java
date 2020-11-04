package br.srv.portal.useful;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Hora {
	
	private static final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
	
	public static LocalTime getAtual() {
		return LocalTime.now(zoneId);
	}
	
	public static String atualFormatada() {
		return getAtual().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

}
