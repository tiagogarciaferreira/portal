package br.srv.portal.services;

import org.springframework.stereotype.Service;

import br.srv.portal.encrypt.Rsa;
import br.srv.portal.encrypt.Sha;
import br.srv.portal.model.UrlSolicitacao;
import br.srv.portal.useful.Data;

@Service
public class ServiceUrl {

	private Rsa rsa = null;

	public String cifrar(UrlSolicitacao urlSolicitacao) {
		rsa = new Rsa();
		StringBuilder urlCifrada = new StringBuilder();
		urlCifrada.append("?id=").append(rsa.crypt(urlSolicitacao.getId()));
		if (!urlSolicitacao.getValidade().isEmpty()) {
			urlCifrada.append("&").append("validade=").append(rsa.crypt(urlSolicitacao.getValidade()));
		}
		return urlCifrada.toString();
	}

	public UrlSolicitacao decifrar(UrlSolicitacao urlSolicitacao) {
		rsa = new Rsa();
		if (!urlSolicitacao.getValidade().isEmpty()) {
			return new UrlSolicitacao(rsa.descrypt(urlSolicitacao.getId().replaceAll(" ", "+")), rsa.descrypt(urlSolicitacao.getValidade().replaceAll(" ", "+")), false);
		}
		return new UrlSolicitacao(rsa.descrypt(urlSolicitacao.getId().replaceAll(" ", "+")), "", false);
	}

	public UrlSolicitacao validar(UrlSolicitacao urlSolicitacao) {
		urlSolicitacao = new ServiceUrl().decifrar(urlSolicitacao);
		urlSolicitacao.setStatusOK(urlSolicitacao.getValidade().equals(Data.atualFormatada()) ? true : false);
		return urlSolicitacao;
	}

	public boolean verificar(Long id, String token) {
		String idMd5 = Sha.getSha(id);
		return (idMd5.equals(token)) ? true : false;
	}
}
