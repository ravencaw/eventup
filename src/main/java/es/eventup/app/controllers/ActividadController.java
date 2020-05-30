package es.eventup.app.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import es.eventup.app.models.entity.Actividad;
import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.service.ActividadService;
import es.eventup.app.models.service.EventoService;

@Controller
@SessionAttributes("actividad")
public class ActividadController {

	
	@Autowired
	private ActividadService service;
	@Autowired
	private EventoService eventoService;
	
	@RequestMapping(value="/actividad/listar/{id_evento}", method=RequestMethod.GET)
	public String listar(Model model, @PathVariable(value="id_evento") Long id_evento) {
		model.addAttribute("tituloWeb", "Actividad: Lista");
		model.addAttribute("id_evento", id_evento);
		model.addAttribute("titulo", "Listado de actividades");
		model.addAttribute("actividades", service.findAll());
		return "actividad/listar";
	}
	
	@RequestMapping(value="/actividad/nuevo/{id_evento}", method=RequestMethod.GET)
	public String crear(Map<String, Object> model, @PathVariable(value="id_evento") Long id_evento) {
		Actividad actividad = new Actividad();
		model.put("actividad", actividad);
		model.put("id_evento", id_evento);
		model.put("tituloWeb", "Actividad: Crear");
		model.put("titulo", "Formulario de Actividad");
		return "actividad/nuevo";
	}
	
	@RequestMapping(value="/actividad/form/{id_evento}/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, @PathVariable(value="id_evento") Long id_evento) {
		
		Optional<Actividad> cli = null;
		
		if(id>0) {
			cli = service.findOne(id);
		}else {
			return "redirect:/actividad/listar/"+id_evento;
		}
		
		model.put("id_evento", id_evento);
		model.put("actividad", cli);
		model.put("tituloWeb", "Actividad: Editar");
		model.put("titulo", "Edicion de Actividad");
		
		return "actividad/nuevo";
	}
	
	@RequestMapping(value="/actividad/nuevo/{id_evento}", method=RequestMethod.POST)
	public String guardar(@Valid Actividad actividad, BindingResult result, Model model,SessionStatus stat, @PathVariable(value="id_evento") Long id_evento) {
		
		if(result.hasErrors()) {
			return "actividad/nuevo/"+id_evento;
		}
		
		Evento e = eventoService.findOne(id_evento).get();
		
		actividad.setEvento(e);
		
		service.save(actividad);
		stat.setComplete();
		return "redirect:/actividad/listar/"+id_evento;
	}
	
	@RequestMapping(value="/actividad/delete/{id_evento}/{id}")
	public String eliminar(@PathVariable(value="id") Long id, @PathVariable(value="id_evento") Long id_evento) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/actividad/listar/"+id_evento;
	}
}
