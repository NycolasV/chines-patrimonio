package br.info.pweb.chines.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_itemPatrimonio")
public class ItemPatrimonio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patrimonio_id", nullable = false)
	private Patrimonio patrimonio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ambienteAtual_id", nullable = false)
	private Ambiente ambienteAtual;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false, unique = false)
	private Date dataAtualizacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ambiente getAmbienteAtual() {
		return ambienteAtual;
	}

	public void setAmbienteAtual(Ambiente ambienteAtual) {
		this.ambienteAtual = ambienteAtual;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
		
}
