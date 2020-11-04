package br.srv.portal.template;

import br.srv.portal.useful.Data;
import br.srv.portal.useful.Hora;

public class HtmlUsuario {
	
	private static final String LOGO = "/img/logo.png";

	public static String novoUsuario(String url, String nome, String senha, String DOMINIO) {
		StringBuilder estruturaHtml = new StringBuilder();
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"background-color: white;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:black;\">");
		estruturaHtml.append("<a href=\""+DOMINIO+"\" ><img src=\""+LOGO+"\" width=\"110px\" height=\"110px\" title=\"Portal 3E\"/></a>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 50px;\">");
		estruturaHtml.append("<label style=\"color: black;\">Olá! "+nome+".</label>");
		estruturaHtml.append("<br> <label style=\"color: black;\">Confirme seu cadastro de usuário.</label>");
		estruturaHtml.append("<br> <label>Sua senha: <b style=\"color: black;\">"+senha+"</b></label> ");
		estruturaHtml.append("<br> <label>Data do cadastro: <b style=\"color: black;\">"+ Data.atualFormatada()+".</b></label> ");
		estruturaHtml.append("<br> <label>Hora do cadastro: <b style=\"color: black;\">"+ Hora.atualFormatada() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>" + "<br>");
		estruturaHtml.append("<button style=\" background-color: #006400;height:50px;width:100%;border-radius:5px;\">" + "<a style=\"text-decoration: none;\" href=\" " + url + " \"+ \"> <b style=\" color:white;\">Confirmar cadastro</b> </a> " + "</button>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<br><br>");
		estruturaHtml.append("<footer style=\"color: black;font-size:10px;\">3E Gestão de Pessoas - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>");
		return estruturaHtml.toString();
	}

	
	public static String novaSenha(String url, String nome, String DOMINIO) {
		StringBuilder estruturaHtml = new StringBuilder();
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"background-color: white;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:black;\">");
		estruturaHtml.append("<a href=\""+DOMINIO+"\" ><img src=\""+LOGO+"\" width=\"110px\" height=\"110px\" title=\"Portal 3E\"/></a>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 50px;\">");
		estruturaHtml.append("<label style=\"color: black;\">Olá! "+nome+".</label>");
		estruturaHtml.append("<br> <label style=\"color: black;\">Redefina sua senha.</label>");
		estruturaHtml.append("<br> <label>Data da solicitação: <b style=\"color: black;\">"+ Data.atualFormatada() +".</b></label>");
		estruturaHtml.append("<br> <label>Hora da solicitação: <b style=\"color: black;\">"+ Hora.atualFormatada() +".</b></label>");
		estruturaHtml.append("<br> <label>Solicitação valida até: <b style=\"color:black;\">"+ Data.atualFormatada() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>" + "<br>");
		estruturaHtml.append("<button style=\" background-color: #006400;height:50px;width:100%;border-radius:5px;\">" + "<a style=\"text-decoration: none;\" href=\" " + url + " \"+ \"> <b style=\" color:white;\">Redefinir senha</b> </a> " + "</button>");
		estruturaHtml.append("<br><br>");
		estruturaHtml.append("<footer style=\"color: black;font-size:10px;\">3E Gestão de Pessoas - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>");
		return estruturaHtml.toString();
	}
	
	public static String novoUsuarioNewsletter(String url, String nome, String DOMINIO) {
		StringBuilder estruturaHtml = new StringBuilder();
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"background-color: white;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:black;\">");
		estruturaHtml.append("<a href=\""+DOMINIO+"\" ><img src=\""+LOGO+"\" width=\"110px\" height=\"110px\" title=\"Portal 3E\"/></a>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 50px;\">");
		estruturaHtml.append("<label style=\"color: black;\">Olá! "+nome+".</label>");
		estruturaHtml.append("<br> <label style=\"color: black;\">Confirme seu cadastro newsletter.</label>");
		estruturaHtml.append("<br> <label>Data do cadastro: <b style=\"color: black;\">"+ Data.atualFormatada()+".</b></label> ");
		estruturaHtml.append("<br> <label>Hora do cadastro: <b style=\"color: black;\">"+ Hora.atualFormatada() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>" + "<br>");
		estruturaHtml.append("<button style=\" background-color: #006400;height:50px;width:100%;border-radius:5px;\">" + "<a style=\"text-decoration: none;\" href=\" " + url + " \"+ \"> <b style=\" color:white;\">Confirmar cadastro</b> </a> " + "</button>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<br><br>");
		estruturaHtml.append("<footer style=\"color: black;font-size:10px;\">3E Gestão de Pessoas - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>");
		return estruturaHtml.toString();
	}

}
