package com.project.consorcio.entity;

import java.time.LocalDate;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_requerimiento")
public class Requerimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_reque")
	private Integer codigo;
	@Column(name = "num_reque")
	private String numero;
	@Column(name = "nom_reque")
	private String nombre;
	@Column(name = "fec_reque")
	private LocalDate fecha;
	@Column(name = "est_reque")
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "cod_usu")
	private Usuario usuario;

	@JsonIgnore
	@OneToMany(mappedBy = "requerimiento")
	private List<RequerimientoBien> listaDetalle;
	
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<RequerimientoBien> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<RequerimientoBien> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	
	
	
}
