package com.project.consorcio.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
//anotación que indica que la clase va a formar parte de una entidad
public class RolEnlacePK{	
	private  int idrol;
	private  int idenlace;
	
	@Override
	public int hashCode() {
		return Objects.hash(idenlace, idrol);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolEnlacePK other = (RolEnlacePK) obj;
		return idenlace == other.idenlace && idrol == other.idrol;
	}
	
	public int getIdrol() {
		return idrol;
	}
	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}
	public int getIdenlace() {
		return idenlace;
	}
	public void setIdenlace(int idenlace) {
		this.idenlace = idenlace;
	}
	
	//insert into tb_rol_enlace values(1,2)
	//	idrol	idenlace		 hascode
	//	 1			2		===> 646464643131
	
	//insert into tb_rol_enlace values(1,3)
	//	idrol	idenlace		 hascode
	//	 1			3		===> 646464643131
	
	
	
	
	
}
