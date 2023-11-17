package com.project.consorcio;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.consorcio.entity.Enlace;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.services.UsuarioServices;

@SpringBootTest
class SpringClinicaGrupo2ApplicationTests {
	@Autowired
	private UsuarioServices servicioUsu;
	
	@Autowired
	private BCryptPasswordEncoder encriptar;
	
	
	@Test
	void contextLoads() {
		
		System.out.println("CLAVE : "+encriptar.encode("123"));
		
		/*Usuario bean=servicioUsu.sesionDelUsuario("maria2025");
		if (bean==null)
			System.out.println("Usuario no existe");
		else {
			List<Enlace> enlaces=servicioUsu.enlacesDelUsuario(bean.getRol().getCodigo());
			enlaces.forEach(e->{
				System.out.println(e.getCodigo()+" - "+e.getDescripcion()+" - "+
									e.getRuta());
			});
		}*/
		
		
	}

}






