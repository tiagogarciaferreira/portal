package br.srv.portal.constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public enum E_Mensagem {
	
	RESPOSTA("?resposta="), 
	DESCRICAO("&descricao="),
	ERRO("erro"),
	SUCESSO("sucesso"),
	
	NOTIFICACAO_CANCELADA(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("O seu cadastro newsletter foi cancelado com sucesso.")),
	NOTIFICACAO_CANCELADA_ERRO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel cancelar o seu cadastro newsletter, tente novamente.")),
	
	CADASTRO_SUCESSO(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("Cadastrado com sucesso.")),
	CADASTRO_SUCESSO_NEWSLETTER(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("Cadastrado com sucesso. Verifique seu (email) caixa de entrada ou spam e confirme seu cadastro.")),
	CADASTRO_ERRO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel realizar o seu cadastro, tente novamente.")),
	CADASTRO_CONFIRMADO(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("Confirmado com sucesso.")),
	CADASTRO_CONFIRMADO_ERRO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel confirmar seu cadastro, tente novamente.")),
	
	UPDATE_SUCESSO(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("Atualizado com sucesso.")),
	UPDATE_ERRO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel atualizar o registro, tente novamente.")),
	
	DELETE_SUCESSO(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("Excluído com sucesso.")),
	DELETE_ERRO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel excluir o registro, tente novamente.")),
	DELETE_ERRO_DEPENDENCIA(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel excluir este registro, o mesmo possue dependências.")),
	
	EMAIL_EXISTENTE	(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Este email já esta cadastrado.")),
	EMAIL_ENVIADO(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("Email enviado com sucesso. Verifique sua caixa de entrada ou spam.")),
	EMAIL_NAO_EXISTE(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel enviar o email, o mesmo não corresponde a nenhum usuário.")),
	EMAIL_INATIVO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Seu cadastro esta inativo, confirme-o primeiro.")),
	EMAIL_ERRO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel enviar o email, tente novamente.")),
	
	SENHA_UPDATE_SUCESSO(RESPOSTA.get()+SUCESSO.get()+DESCRICAO.get()+encode("Atualizada com sucesso.")),
	SENHA_UPDATE_ERRO(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Não foi possivel atualizar a sua senha, tente novamente.")),
	SENHA_UPDATE_EXPIRADA(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Sua solicitação expirou, tente novamente.")),
	NOTICIA_INDISPONIVEL(RESPOSTA.get()+ERRO.get()+DESCRICAO.get()+encode("Desculpe! Solicitação não encontrada."));
	
	private String value;
	
	private E_Mensagem(String string) {
		this.value = string;
	}
	
	public static String encode(String descricao) {
		try {
			return URLEncoder.encode(descricao, StandardCharsets.UTF_8.toString()); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String get(){
		return value;
	}
}
