package br.srv.portal.model;

import java.io.Serializable;
import javax.persistence.*;

import br.srv.portal.encrypt.Sha;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long idUsuario;
	private String email;
	@Column(name="nome_completo")
	private String nomeCompleto;
	private String senha = "";
	private Boolean status;
	private String telefone;
	@ManyToOne
	@JoinColumn(name="tipo_acesso_id")
	private TipoAcesso tipoAcesso;
	@Transient
	private String token;
	
	public Usuario() {
	}
	
	public Usuario(Long idUsuario, String nomeCompleto) {
		this.idUsuario = idUsuario;
		this.nomeCompleto = nomeCompleto;
	}
	
	public Usuario(String nome) {
		this.nomeCompleto = nome;
	}
	
	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(Long idUsuario, String nomeCompleto, String tipoAcesso) {
		this.idUsuario = idUsuario;
		this.nomeCompleto = nomeCompleto;
		this.tipoAcesso = new TipoAcesso(tipoAcesso);
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoAcesso getTipoAcesso() {
		return this.tipoAcesso;
	}

	public String getToken() {
		this.token = (this.idUsuario != null && token == null) ? Sha.getSha(this.idUsuario) : token;
		return token;
	}
	
	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
	
}