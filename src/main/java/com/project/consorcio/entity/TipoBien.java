package com.project.consorcio.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_tipo_bien")
public class TipoBien {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="cod_tipo_bien")
	private Integer codigo ;
	
	@Column(name="des_tipo_bien")
	private String descripcion;

	@JsonIgnore
	@OneToMany(mappedBy = "tipo")
	private List<Bien> listaBien;
	
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

	public List<Bien> getListaBien() {
		return listaBien;
	}

	public void setListaBien(List<Bien> listaBien) {
		this.listaBien = listaBien;
	}
	
	
	
	
}
