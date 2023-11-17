package com.project.consorcio.entity;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name="tb_rol_enlace")
public class RolEnlace {
	
	//atributo de la clase RolEnlacePK
	@EmbeddedId
	private RolEnlacePK pk;
	
	@ManyToOne
	@JoinColumn(name = "idrol",insertable = false,updatable = false,referencedColumnName ="idrol")
	private Rol rol;

	@ManyToOne
	@JoinColumn(name="idenlace",insertable = false,updatable = false,referencedColumnName = "idenlace")
	private Enlace enlace;


	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Enlace getEnlace() {
		return enlace;
	}
	public void setEnlace(Enlace enlace) {
		this.enlace = enlace;
	}
	public RolEnlacePK getPk() {
		return pk;
	}
	public void setPk(RolEnlacePK pk) {
		this.pk = pk;
	}
	
	
	
}