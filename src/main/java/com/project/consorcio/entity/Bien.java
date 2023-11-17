package com.project.consorcio.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_bien")
public class Bien {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="cod_bien")
	private Integer codigo ;
	
	@Column(name="des_bien")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "cod_tipo_bien")
	private TipoBien tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bien")
	private List<RequerimientoBien> listaRequerimientos;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoBien getTipo() {
		return tipo;
	}

	public void setTipo(TipoBien tipo) {
		this.tipo = tipo;
	}

	public List<RequerimientoBien> getListaRequerimientos() {
		return listaRequerimientos;
	}

	public void setListaRequerimientos(List<RequerimientoBien> listaRequerimientos) {
		this.listaRequerimientos = listaRequerimientos;
	}
	
	
	
	
}
