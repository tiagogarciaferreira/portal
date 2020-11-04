package br.srv.portal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import br.srv.portal.encrypt.Sha;

@Entity
@Table(name = "noticia")
public class Noticia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticia")
	private Long idNoticia;
	@Column(name = "data_cadastro", insertable = false, updatable = false)
	private LocalDateTime dataCadastro;
	private Boolean disponibilidade;
	@Column(name = "em_local")
	private String emLocal;
	private String texto;
	private String titulo;
	
	@ManyToMany
	@JoinTable(name = "noticia_imagem", joinColumns = { @JoinColumn(name = "noticia_id") }, inverseJoinColumns = {@JoinColumn(name = "imagem_id") })
	private List<Imagem> imagens;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@Version
	private Long version;
	@Transient
	private String token;
	
	public Noticia() {
		
	}

	public Noticia(Boolean disponivel) {
		this.disponibilidade = disponivel;
	}
	
	public Noticia(Categoria categoria, Boolean disponivel) {
		this.categoria = categoria;
		this.disponibilidade = disponivel;
	}
	
	public Noticia(Long idnoticia) {
		this.idNoticia = idnoticia;
	}

	public Noticia(String titulo) {
		this.titulo = titulo;
	}

	public Long getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(Long idNoticia) {
		this.idNoticia = idNoticia;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getEmLocal() {
		return emLocal;
	}

	public void setEmLocal(String emLocal) {
		this.emLocal = emLocal;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getToken() {
		this.token = (this.idNoticia != null && token == null) ? Sha.getSha(this.idNoticia) : token;
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNoticia == null) ? 0 : idNoticia.hashCode());
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
		Noticia other = (Noticia) obj;
		if (idNoticia == null) {
			if (other.idNoticia != null)
				return false;
		} else if (!idNoticia.equals(other.idNoticia))
			return false;
		return true;
	}
	
}
