package com.project.consorcio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.consorcio.entity.Requerimiento;
import com.project.consorcio.entity.RequerimientoBien;
import com.project.consorcio.entity.RequerimientoBienPK;
import com.project.consorcio.repository.RequerimientoBienRepository;
import com.project.consorcio.repository.RequerimientoRepository;

@Service
public class RequerimientoServices {
	@Autowired
	private RequerimientoRepository repoReque;
	
	@Autowired
	private RequerimientoBienRepository repoDet;
	
	@Transactional
	public void saveRequerimiento(Requerimiento bean) {
		try {
			//grabar requerimiento
			repoReque.save(bean);	
			
			//bucle para realizar recorrido sobre el atributo listaDetalle
			for(RequerimientoBien rb:bean.getListaDetalle()){
				//crear la llave
				RequerimientoBienPK llave=new RequerimientoBienPK();
				//setear objeto llave 
				llave.setCod_reque(bean.getCodigo());
				llave.setCod_bien(rb.getBien().getCodigo());
				//enviar "llave" al objeto "rb"
				rb.setPk(llave);
				//grabar detalle requerimiento
				repoDet.save(rb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}





