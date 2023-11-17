package com.project.consorcio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.consorcio.entity.Laboratorio;
import com.project.consorcio.entity.TipoMedicamento;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {
	//select *from tb_tipo_medicamento where cod_lab=1
	//JHQL--->sentencias SQL de JPA
	@Query("select tm from TipoMedicamento tm where tm.laboratorio.codigo=?1")
	public List<TipoMedicamento> listarTipoMedicamentoPorLaboratorio(Integer cod);
}











