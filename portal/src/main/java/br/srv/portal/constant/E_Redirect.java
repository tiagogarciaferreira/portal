package br.srv.portal.constant;

public enum E_Redirect {
	
	URL_REDEFINIR_SENHA("/publico/redefinir-senha.html"),
	URL_EMAIL_REDEFINIR_SENHA("/publico/email-redefinir-senha.html"),
	URL_MAP_EMAIL_REDEFINIR_SENHA("redirect:/senha/email-redefinir-senha"),
	
	URL_MAP_REDEFINIR_SENHA("redirect:/senha/redefinir-senha"),
	
	URL_CATEGORIA_CADASTRO("/admin/CadastroCategoria.html"),
	URL_CATEGORIA_PESQUISA("/admin/PesquisaCategoria.html"),
	URL_MAP_CATEGORIA_LISTAR("redirect:/categoria/listar"),
	
	URL_CONTATO("/publico/contato.html"),
	URL_MAP_CONTATO("redirect:/contato/cadastrar"),
	URL_CONTATO_LISTAR("/admin/PesquisaContato.html"),
	URL_MAP_CONTATO_LISTAR("redirect:/contato/listar"),
	
	URL_DASHBOARD("/admin/dashboard.html"),
	URL_MAP_DASHBOARD("redirect:/dashboard/home"),
	
	URL_IMAGEM("/admin/PesquisaImagem.html"),
	URL_MAP_IMAGEM_LISTAR("redirect:/imagem/listar"),
	
	INDEX("index"),
	
	URL_LOGIN("/publico/login.html"),
	URL_MAP_LOGIN("redirect:/publico/login"),
	
	URL_SOBRE("/publico/sobre.html"),
	URL_MAP_SOBRE("redirect:/publico/sobre"),
	
	URL_ESTAGIO_FUNCIONAMENTO("/publico/estagio-como-funciona.html"),
	URL_MAP_ESTAGIO_FUNCIONAMENTO("redirect:/estagio/visualizar"),
	URL_PROCESSO_SELETIVO("/publico/processo-seletivo-como-funciona.html"),
	URL_MAP_PROCESSO_SELETIVO("redirect:/processo-seletivo/visualizar"),
	
	URL_RESULTADO("/publico/resultado.html"),
	URL_MAP_RESULTADO("redirect:/resultado/visualizar"),
	
	URL_NEWSLETTER("/admin/PesquisaNewsletter.html"),
	URL_MAP_NEWSLETTER_LISTAR("redirect:/newsletter/listar"),
	INDEX_REDIRECT("redirect:/"),
	
	URL_NOTICIA_LISTAR("/admin/PesquisaNoticia.html"),
	URL_MAP_NOTICIA_LISTAR("redirect:/noticia/listar"),
	
	URL_NOTICIA_CADASTRO("/admin/CadastroNoticia.html"),
	URL_MAP_NOTICIA_CADASTRO("redirect:/noticia/cadastrar"),
	
	URL_NOTICIA_LER("/publico/ler-noticia.html"),
	URL_MAP_ALL_NOTICIA("redirect:/noticia/listar-noticias"),
	URL_ALL_NOTICIA("/publico/lista-noticias.html"),
	
    URL_USUARIO_LISTAR("/admin/PesquisaUsuario.html"),
	URL_MAP_USUARIO_LISTAR("redirect:/usuario/listar"),
    URL_USUARIO_CADASTRO("/admin/CadastroUsuario.html"),
	URL_MAP_USUARIO_CADASTRO("redirect:/usuario/cadastrar"),
	
    URL_PERFIL("/admin/UsuarioPerfil.html"),
	URL_MAP_USUARIO_PERFIL("redirect:/perfil/visualizar"),
	
	URL_MAP_ERROR("redirect:/error"),
	URL_ERRO("/error/error.html");

	private String value;
	
	private E_Redirect(String string) {
		this.value = string;
	}
	
	public String get() {
		return value;
	}
	
}
