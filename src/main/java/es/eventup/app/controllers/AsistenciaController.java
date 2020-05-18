package es.eventup.app.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import es.eventup.app.models.entity.Asistencia;
import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.service.AsistenciaService;
import es.eventup.app.models.service.EntradaService;

@Controller
@SessionAttributes("asistencia")
public class AsistenciaController {

	
	@Autowired
	private AsistenciaService service;
	@Autowired
	private EntradaService entradaService;
	
	@RequestMapping(value="/asistencia/listar/{id_evento}", method=RequestMethod.GET)
	public String listar(Model model, @PathVariable Long id_evento) {
		model.addAttribute("id_evento", id_evento);
		model.addAttribute("tituloWeb", "Asistencia: Lista");
		model.addAttribute("titulo", "Listado de asistencias");
		model.addAttribute("asistencias", service.findAll());
		return "asistencia/listar";
	}
	
	@RequestMapping(value="/asistencia/nuevo/{id_evento}", method=RequestMethod.GET)
	public String crear(Map<String, Object> model, @PathVariable Long id_evento) {
		Asistencia asistencia = new Asistencia();
		model.put("asistencia", asistencia);
		model.put("id_evento", id_evento);
		model.put("tituloWeb", "Asistencia: Crear");
		model.put("titulo", "Formulario de Asistencia");
		return "asistencia/nuevo";
	}
	
	@RequestMapping(value="/asistencia/nuevo/{id_evento}/{id_entrada}")
	public String guardar(SessionStatus stat, @PathVariable("id_entrada") Long id_entrada, @PathVariable("id_evento") Long id_evento) {
		
		Asistencia asistencia = new Asistencia();
		Entrada entrada = entradaService.findOne(id_entrada).get();
		
		Date fecha = new Date();
		Date hora = new Date();
		
		asistencia.setEntrada(entrada);
		asistencia.setFecha(fecha);
		asistencia.setHora(hora);
		
		service.save(asistencia);
		
		entrada.setAsistencia(asistencia);
		
		entradaService.save(entrada);
		
		stat.setComplete();
		return "redirect:/asistencia/listar/"+id_evento;
	}
	
	@RequestMapping(value="/asistencia/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/asistencia/listar";
	}
	
}
