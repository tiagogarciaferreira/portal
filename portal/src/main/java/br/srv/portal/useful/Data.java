package br.srv.portal.useful;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Data {
	
	private static final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
	
	public static LocalDate getAtual() {
		return LocalDate.now(zoneId);
	}
	
	public static String atualFormatada() {
		return getAtual().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	

}
