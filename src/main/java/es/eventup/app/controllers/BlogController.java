package es.eventup.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;


import es.eventup.app.models.entity.Actividad;
import es.eventup.app.models.service.ActividadService;

@Controller
@SessionAttributes("blog")
public class BlogController {

	
	@Autowired
	private ActividadService service;
	
	@RequestMapping(value="/actividad/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Actividad: Lista");
		model.addAttribute("titulo", "Listado de actividades");
		model.addAttribute("actividad", service.findAll());
		return "actividad/listar";
	}
	
	@RequestMapping(value="/actividad/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Actividad actividad = new Actividad();
		model.put("evento", actividad);
		model.put("tituloWeb", "Actividad: Crear");
		model.put("titulo", "Formulario de Actividad");
		return "actividad/nuevo";
	}
	
	@RequestMapping(value="/actividad/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Actividad> cli = null;
		
		if(id>0) {
			cli = service.findOne(id);
		}else {
			return "redirect:/actividad/listar";
		}
		
		model.put("actividad", cli);
		model.put("tituloWeb", "Actividad: Editar");
		model.put("titulo", "Edicion de Actividad");
		
		return "actividad/nuevo";
	}
	
	@RequestMapping(value="/actividad/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid Actividad actividad, BindingResult result, Model model,SessionStatus stat) {
		
		if(result.hasErrors()) {
			return "actividad/nuevo";
		}
		
		service.save(actividad);
		stat.setComplete();
		return "redirect:/actividad/listar";
	}
	
	@RequestMapping(value="/actividad/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/actividad/listar";
	}
}
