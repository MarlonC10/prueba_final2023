package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Bien;
import com.project.consorcio.repository.BienRepository;

@Service
public class BienServices {
	@Autowired
	private BienRepository repo;
	
	public List<Bien> binesPorTipo(int codTipo){
		return repo.listarBienesPorTipo(codTipo);
	}
	
}
