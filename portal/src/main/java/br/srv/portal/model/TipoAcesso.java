package br.srv.portal.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tipo_acesso")
public class TipoAcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_acesso")
	private Long idTipoAcesso;
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tipo_acesso_permissao", joinColumns = {
			@JoinColumn(name = "tipo_acesso_id") }, inverseJoinColumns = { @JoinColumn(name = "permissao_id") })
	private List<Permissao> permissoes;

	public TipoAcesso() {
	}

	public TipoAcesso(String nome) {
		this.nome = nome;
	}

	public Long getIdTipoAcesso() {
		return this.idTipoAcesso;
	}

	public void setIdTipoAcesso(Long idTipoAcesso) {
		this.idTipoAcesso = idTipoAcesso;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoAcesso == null) ? 0 : idTipoAcesso.hashCode());
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
		TipoAcesso other = (TipoAcesso) obj;
		if (idTipoAcesso == null) {
			if (other.idTipoAcesso != null)
				return false;
		} else if (!idTipoAcesso.equals(other.idTipoAcesso))
			return false;
		return true;
	}

}