package com.project.consorcio.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RequerimientoBienPK {
	private  int cod_reque;
	private  int cod_bien;
	
	@Override
	public int hashCode() {
		return Objects.hash(cod_bien, cod_reque);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequerimientoBienPK other = (RequerimientoBienPK) obj;
		return cod_bien == other.cod_bien && cod_reque == other.cod_reque;
	}
	public int getCod_reque() {
		return cod_reque;
	}
	public void setCod_reque(int cod_reque) {
		this.cod_reque = cod_reque;
	}
	public int getCod_bien() {
		return cod_bien;
	}
	public void setCod_bien(int cod_bien) {
		this.cod_bien = cod_bien;
	}
	
}
