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
@Table(name="tb_tipo_medicamento")
public class TipoMedicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_tipo")
	private Integer codigo;
	@Column(name = "nom_tipo")
	private String nombre;
	
	//anular JSON del atributo 
	@JsonIgnore
	//relación uno a muchos
	@OneToMany(mappedBy = "tipo")//ASOC
	private List<Medicamento> listaTipoMedicamento;
	//
	@ManyToOne
	@JoinColumn(name = "cod_lab")
	private Laboratorio laboratorio;//ASC
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Medicamento> getListaTipoMedicamento() {
		return listaTipoMedicamento;
	}

	public void setListaTipoMedicamento(List<Medicamento> listaTipoMedicamento) {
		this.listaTipoMedicamento = listaTipoMedicamento;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	
}
