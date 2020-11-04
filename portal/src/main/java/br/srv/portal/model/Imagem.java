package br.srv.portal.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "imagem")
public class Imagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagem")
	private Long idImagem;
	private String nome;
	@ManyToMany(mappedBy = "imagens")
	private List<Noticia> noticias;
	
	public Imagem() {
	}

	public Imagem(String nameArquivo) {
		this.nome = nameArquivo;
	}

	public Long getIdImagem() {
		return this.idImagem;
	}

	public void setIdImagem(Long idImagem) {
		this.idImagem = idImagem;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Noticia> getNoticias() {
		return this.noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idImagem == null) ? 0 : idImagem.hashCode());
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
		Imagem other = (Imagem) obj;
		if (idImagem == null) {
			if (other.idImagem != null)
				return false;
		} else if (!idImagem.equals(other.idImagem))
			return false;
		return true;
	}

}