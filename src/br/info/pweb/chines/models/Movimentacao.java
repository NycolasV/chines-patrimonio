package br.info.pweb.chines.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_movimentacao")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private ItemPatrimonio item;
	
	@ManyToOne
	@JoinColumn(name = "ambienteOrigem_id", nullable = false)
	private Ambiente ambienteOrigem;
	
	@ManyToOne
	@JoinColumn(name = "ambienteDestino_id", nullable = false)
	private Ambiente ambienteDestino;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false, unique = false)
	private Date dataMovimentacao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemPatrimonio getItem() {
		return item;
	}

	public void setItem(ItemPatrimonio item) {
		this.item = item;
	}

	public Ambiente getAmbienteOrigem() {
		return ambienteOrigem;
	}

	public void setAmbienteOrigem(Ambiente ambienteOrigem) {
		this.ambienteOrigem = ambienteOrigem;
	}

	public Ambiente getAmbienteDestino() {
		return ambienteDestino;
	}

	public void setAmbienteDestino(Ambiente ambienteDestino) {
		this.ambienteDestino = ambienteDestino;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
