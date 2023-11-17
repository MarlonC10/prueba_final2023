package com.project.consorcio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_detalle_requerimiento")
public class RequerimientoBien {
	
	@EmbeddedId
	private RequerimientoBienPK pk;
	
	@ManyToOne
	@JoinColumn(name = "cod_reque",insertable = false,updatable = false,referencedColumnName ="cod_reque")
	private Requerimiento requerimiento;

	@ManyToOne
	@JoinColumn(name="cod_bien",insertable = false,updatable = false,referencedColumnName = "cod_bien")
	private Bien bien;

	@Column(name = "can")
	private int cantidad;
	
	public RequerimientoBienPK getPk() {
		return pk;
	}

	public void setPk(RequerimientoBienPK pk) {
		this.pk = pk;
	}

	public Requerimiento getRequerimiento() {
		return requerimiento;
	}

	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = requerimiento;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
