package br.srv.portal.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "permissao")
public class Permissao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_permissao")
	private Long idPermissao;
	private String nome;
	
	@ManyToMany(mappedBy="permissoes")
	private List<TipoAcesso> tipoAcessos;

	public Permissao() {
	}

	public Long getIdPermissao() {
		return this.idPermissao;
	}

	public void setIdPermissao(Long idPermissao) {
		this.idPermissao = idPermissao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoAcesso> getTipoAcessos() {
		return tipoAcessos;
	}

	public void setTipoAcessos(List<TipoAcesso> tipoAcessos) {
		this.tipoAcessos = tipoAcessos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPermissao == null) ? 0 : idPermissao.hashCode());
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
		Permissao other = (Permissao) obj;
		if (idPermissao == null) {
			if (other.idPermissao != null)
				return false;
		} else if (!idPermissao.equals(other.idPermissao))
			return false;
		return true;
	}

}