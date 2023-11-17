package com.project.consorcio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.dto.Detalle;
import com.project.consorcio.entity.Bien;
import com.project.consorcio.entity.Requerimiento;
import com.project.consorcio.entity.RequerimientoBien;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.services.BienServices;
import com.project.consorcio.services.RequerimientoServices;
import com.project.consorcio.services.TipoBienServices;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/requerimiento")
public class RequerimientoController {
	@Autowired
	private TipoBienServices servicioTipo;
	
	@Autowired
	private BienServices servicioBien;
	
	@Autowired
	private RequerimientoServices servicioReque;
	
	@RequestMapping("/lista")
	public String lista(Model model){
		model.addAttribute("tipos",servicioTipo.listarTodos());		
		return "requerimiento";
	}
	
	@RequestMapping("/consultaBienes")
	@ResponseBody
	public List<Bien> consultaBienes(@RequestParam("codigo") int cod){
		return servicioBien.binesPorTipo(cod);
	}	
	
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<Detalle> adicionar(@RequestParam("codigo") int cod,
							@RequestParam("nombre") String nom,
							@RequestParam("cantidad") int can,
							HttpServletRequest request){
		//declarar
		List<Detalle> data=null;
		//validar si existe el atributo de tipo sesión "lista"
		if(request.getSession().getAttribute("lista")==null)//no existe atributo "lista"
			//crear data
			data=new ArrayList<Detalle>();
		else//existe atributo "lista"
			//recuperar el atributo "lista"
			data=(List<Detalle>) request.getSession().getAttribute("lista");
		
		//crear objeto de la clase Detalle
		Detalle det=new Detalle();
		//setear
		det.setCodigo(cod);
		det.setNombre(nom);
		det.setCantidad(can);
		//adcionar "det" dentro de data
		data.add(det);
		//crear atributo "lista"
		request.getSession().setAttribute("lista",data);
		return data;
	}
	@RequestMapping("/detalles")
	@ResponseBody
	public List<Detalle> detalles(HttpServletRequest request){
		List<Detalle> data=(List<Detalle>) request.getSession().getAttribute("lista");
		return data;
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("numero") String num,
					@RequestParam("descripcion") String des,
					@RequestParam("fecha") String fec,
					@RequestParam("estado") String est,
					HttpServletRequest request,
					RedirectAttributes redirect) {
		try {
			//crear objeto de la entidad Requerimiento
			Requerimiento data=new Requerimiento();
			//setear data
			data.setNumero(num);//RE-0000001
			data.setNombre(des);
			data.setFecha(LocalDate.parse(fec));
			data.setEstado(est);
			//recuperar atributo de tipo sesión "CODIGOUSUARIO"
			int cod=(int) request.getSession().getAttribute("CODIGOUSUARIO");
			//crear objeto de Usuario
			Usuario u=new Usuario();
			//setear "u"
			u.setCodigo(cod);
			//enviar "u" dentro de data
			data.setUsuario(u);
			
			//crear arreglo de objeto
			List<RequerimientoBien> detalle=new 
						ArrayList<RequerimientoBien>();
			
			//recuperar atributo de tipo sesión lista
			List<Detalle> info=(List<Detalle>) request.getSession().getAttribute("lista");
			//bucle
			for(Detalle det:info) {
				//crear objeto de RequerimientoBien
				RequerimientoBien rb=new RequerimientoBien();
				//crear objeto de Bien
				Bien b=new Bien();
				//setear "b"
				b.setCodigo(det.getCodigo());
				//enviar "b" dentro de "rb"
				rb.setBien(b);
				rb.setCantidad(det.getCantidad());
				//enviar rb dentro de "detalle"
				detalle.add(rb);
			}
			//enviar "detalle" dentro "listaDetalle"
			data.setListaDetalle(detalle);
			//grabar
			servicioReque.saveRequerimiento(data);
			//limpiar info
			info.clear();
			request.getSession().setAttribute("lista",info);
			//mensaje
			redirect.addFlashAttribute("MENSAJE","Requerimiento registrado");
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registro");
			e.printStackTrace();
		}
		return "redirect:/requerimiento/lista";
	}
	
	@RequestMapping("/eliminar")
	@ResponseBody
	public List<Detalle> eliminar(@RequestParam("codigo") int cod,
							HttpServletRequest request){
		List<Detalle> data=(List<Detalle>) request.getSession().getAttribute("lista");
		for(Detalle d:data) {
			if(d.getCodigo()==cod) {
				data.remove(d);
				break;
			}
		}
		request.getSession().setAttribute("lista",data);
		return data;
	}
	
	
}























