package br.srv.portal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import br.srv.portal.encrypt.Sha;

@Entity
@Table(name = "contato")
public class Contato implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contato")
	private Long idContato;
	private String assunto;
	@Temporal(TemporalType.DATE)
	@Column(name="data_cadastro", insertable = false, updatable = false)
	private Date dataCadastro;
	private String email;
	private String mensagem;
	@Column(name="nome_completo")
	private String nomeCompleto;
	private Boolean respondido;
	private String tipo;
	
	@Transient
	private String token;
	
	public Contato() {
	}

	public Contato(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Long getIdContato() {
		return this.idContato;
	}

	public void setIdContato(Long idContato) {
		this.idContato = idContato;
	}

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Boolean getRespondido() {
		return this.respondido;
	}

	public void setRespondido(Boolean respondido) {
		this.respondido = respondido;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getToken() {
		this.token = (this.idContato != null && token == null) ? Sha.getSha(this.idContato) : token;
		return token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idContato == null) ? 0 : idContato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (idContato == null) {
			if (other.idContato != null)
				return false;
		} else if (!idContato.equals(other.idContato))
			return false;
		return true;
	}
	
}
