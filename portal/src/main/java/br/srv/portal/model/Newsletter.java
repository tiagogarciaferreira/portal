package br.srv.portal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import br.srv.portal.encrypt.Sha;


@Entity
@Table(name = "newsletter")
public class Newsletter implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_newsletter")
	private Long idNewsletter;
	@Temporal(TemporalType.DATE)
	@Column(name="data_cadastro", insertable = false, updatable = false)
	private Date dataCadastro;
	private String email;
	@Column(name="nome_completo")
	private String nomeCompleto;
	private Boolean status;
	
	@Transient
	private String token;

	public Newsletter() {
	}

	public Newsletter(String email) {
		this.email = email.toLowerCase();
	}

	public Long getIdNewsletter() {
		return this.idNewsletter;
	}

	public void setIdNewsletter(Long idNewsletter) {
		this.idNewsletter = idNewsletter;
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

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getToken() {
		this.token = (this.idNewsletter != null && token == null) ? Sha.getSha(this.idNewsletter) : token;
		return token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNewsletter == null) ? 0 : idNewsletter.hashCode());
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
		Newsletter other = (Newsletter) obj;
		if (idNewsletter == null) {
			if (other.idNewsletter != null)
				return false;
		} else if (!idNewsletter.equals(other.idNewsletter))
			return false;
		return true;
	}

}
