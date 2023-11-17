package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Enlace;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario sesionDelUsuario(String vLogin) {
		return repo.iniciarSesion(vLogin);
	}
	public List<Enlace> enlacesDelUsuario(String desRol){
		return repo.traerEnlacesDelUsuario(desRol);
	}
	
}
