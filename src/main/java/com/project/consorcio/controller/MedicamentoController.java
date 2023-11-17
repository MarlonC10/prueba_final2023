package com.project.consorcio.controller;

import java.io.File;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Laboratorio;
import com.project.consorcio.entity.Medicamento;
import com.project.consorcio.entity.TipoMedicamento;
import com.project.consorcio.services.LaboratorioServices;
import com.project.consorcio.services.MedicamentoServices;
import com.project.consorcio.services.TipoMedicamentoServices;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

//Controller, permite que la vista envie un Request y pueda acceder
//a las direntes direcciones URL's
@Controller
//RequestMapping, permite crear direcciones URL para que el cliente acceda
@RequestMapping("/medicamento")
public class MedicamentoController {
	@Autowired
	private MedicamentoServices servicioMed;
	
	@Autowired
	private TipoMedicamentoServices servicioTipo;
	
	@Autowired
	private LaboratorioServices servicioLab;
	
	
	//crear dirección URL para mostrar la página "medicamento.html"
	@RequestMapping("/lista")
	public String index(Model model){
		//crear atributo
		model.addAttribute("medicamentos",servicioMed.listarTodos());
		model.addAttribute("laboratorios",servicioLab.listarTodos());
		return "medicamento";
	}
	//crear dirección URL para registrar Medicamento
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer codi,
						 @RequestParam("nombre") String nom,
						 @RequestParam("descripcion") String des,
						 @RequestParam("stock") int stock,
						 @RequestParam("precio") double pre,
						 @RequestParam("fecha") String fecha,
						 @RequestParam("tipo") Integer cod,
						 RedirectAttributes redirect) {	
		try {
			//crear objeto de la entidad Medicamento
			Medicamento med=new Medicamento();
			//setear atributos del objeto "med" usando los parámetros
			med.setNombre(nom);
			med.setDescripcion(des);
			med.setStock(stock);
			med.setPrecio(pre);
			med.setFecha(LocalDate.parse(fecha));
			//crear objeto de le entidad TipoMedicamento
			TipoMedicamento tp=new TipoMedicamento();
			//setear atributo codigo
			tp.setCodigo(cod);
			//enviar objeto "tp" al objeto "med"
			med.setTipo(tp);
			//validar codi
			if(codi==0) {
				//invocar al método registrar
				servicioMed.registrar(med);
				//mensaje +
				redirect.addFlashAttribute("MENSAJE","Medicamento registrado");
			}
			else {
				//setear atributo codigo
				med.setCodigo(codi);
				servicioMed.actualizar(med);
				//mensaje +
				redirect.addFlashAttribute("MENSAJE","Medicamento actualizado");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/medicamento/lista";
	}
	//crear dirección URL para buscar Medicamento por código
	@RequestMapping("/consultaPorID")
	@ResponseBody
	public Medicamento consultaPorID(@RequestParam("codigo") Integer cod){
		return servicioMed.buscarPorID(cod);
	}
	//crear dirección URL para eliminar Medicamento por código
	@RequestMapping("/eliminarPorID")
	public String eliminarPorID(@RequestParam("codigo") Integer cod,
								RedirectAttributes redirect) {
		servicioMed.eliminar(cod);
		redirect.addFlashAttribute("MENSAJE","Medicamento eliminado");
		return "redirect:/medicamento/lista";
	}
	
	//crear dirección URL para listar Tipos de Medicamento según código de laboratorio
	@RequestMapping("/consultaTipoPorLab")
	@ResponseBody
	public List<TipoMedicamento> consultaTipoPorLab(@RequestParam("codigo") Integer cod) {
		return servicioLab.listarTiposPorLaboratorio(cod);
	}
	
	@RequestMapping("/reporteMedicamentos")
	public void medicamentos(HttpServletResponse response) {
		try {
			//invocar al método listarTodos
			List<Medicamento> lista=servicioMed.listarTodos();
			//acceder al reporte "reporteMedicamento.jrxml"
			File file=ResourceUtils.getFile("classpath:medicamento.jrxml");
			//crear objeto de la clase JasperReport y manipular el objeto file
			JasperReport jasper=JasperCompileManager.compileReport(file.getAbsolutePath());
			//origen de datos "manipular lista"
			JRBeanCollectionDataSource origen=new JRBeanCollectionDataSource(lista);
			//crear reporte
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasper,null,origen);
			//salida del reporte en formato PDF
			response.setContentType("application/pdf");
			//
			OutputStream salida=response.getOutputStream();
			//exportar a pdf
			JasperExportManager.exportReportToPdfStream(jasperPrint,salida);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}






