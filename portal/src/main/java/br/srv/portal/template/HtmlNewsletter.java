package br.srv.portal.template;

import java.util.List;
import br.srv.portal.constant.E_Solicitacao;
import br.srv.portal.model.Noticia;

public class HtmlNewsletter {
	
	private static final String LOGO = "/img/logo.png";
	
	public static String novaNewsletter(List<Noticia> noticias, String DOMINIO) {
		StringBuilder estruturaHtml = new StringBuilder();
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"background-color: white;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:black;\">");
		estruturaHtml.append("<a href=\""+DOMINIO+"\" ><img src=\""+DOMINIO+LOGO+"\" width=\"110px\" height=\"110px\" title=\"Portal 3E\"/></a>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<table>");
		for(Noticia noticia: noticias) {
			String IMAGEM = DOMINIO + "/img/noticia.jpg"; 
			estruturaHtml.append("<tr>");
				estruturaHtml.append("<td> <img src=\""+IMAGEM+"\" width=\"100px\" height=\"100px\" title=\"Notícia\"/> </td>");
				estruturaHtml.append("<td>");
					estruturaHtml.append("<div style=\"text-align: left; margin-left: 25px;\">");
					estruturaHtml.append("<label>Notícia: <b style=\"color: black;\">"+noticia.getTitulo()+".</b></label> ");
					estruturaHtml.append("<br> <label>Assunto: <b style=\"color: black;\">"+noticia.getCategoria().getNome()+".</b></label>");
					estruturaHtml.append("<br> <label>Em: <b style=\"color: black;\">"+noticia.getEmLocal()+".</b></label>");
					estruturaHtml.append("<br> <a style=\"cursor: pointer;\" href=\""+DOMINIO+E_Solicitacao.LER_NOTICIA.get()+noticia.getIdNoticia()+"/?text="+noticia.getTitulo()+"&token="+noticia.getToken() + "\"><b style=\"cursor: pointer;\"><button style=\"border: solid;border-radius: 3px; background-color: green;border-color: transparent;color: white;cursor: pointer;\">Ler mais</button></b></a>");
					estruturaHtml.append("</div>");
					estruturaHtml.append("<hr>");
				estruturaHtml.append("</td>");
			estruturaHtml.append("</tr>");
		}
		estruturaHtml.append("</table>");
		return estruturaHtml.toString();
	}
	
}
