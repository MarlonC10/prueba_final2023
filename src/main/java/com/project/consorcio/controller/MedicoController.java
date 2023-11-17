package com.project.consorcio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.consorcio.services.DistritoServices;
import com.project.consorcio.services.EspecialidadServices;
import com.project.consorcio.services.MedicoServices;
import com.project.consorcio.services.SedeServices;

//Controller, permite que la vista envie un Request y pueda acceder
//a las direntes direcciones URL's
@Controller
//RequestMapping, permite crear direcciones URL para que el cliente acceda
@RequestMapping("/medico")
public class MedicoController {
	@Autowired
	private MedicoServices servicioMed;
	
	@Autowired
	private SedeServices servicioSede;
	
	@Autowired
	private DistritoServices servicioDis;
	
	@Autowired
	private EspecialidadServices servicioEspe;
	
	
	
	//crear dirección URL para mostrar la página "medicamento.html"
	@RequestMapping("/lista")
	public String index(Model model){
		//crear atributo
		model.addAttribute("medicos",servicioMed.listarTodos());
		model.addAttribute("sedes",servicioSede.listarTodos());
		model.addAttribute("distritos",servicioDis.listarTodos());
		model.addAttribute("especialidades",servicioEspe.listarTodos());
		return "medico";
	}
	
}
