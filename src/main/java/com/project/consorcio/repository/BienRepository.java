package com.project.consorcio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.consorcio.entity.Bien;

public interface BienRepository extends JpaRepository<Bien, Integer>{
	
	@Query("select b from Bien b where b.tipo.codigo=?1")
	List<Bien> listarBienesPorTipo(int codTipo);
	
	
}
