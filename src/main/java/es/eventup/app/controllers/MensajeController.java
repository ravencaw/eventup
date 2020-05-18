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


import es.eventup.app.models.entity.Mensaje;
import es.eventup.app.models.service.MensajeService;

@Controller
@SessionAttributes("mensaje")
public class MensajeController {

	
	@Autowired
	private MensajeService service;
	
	@RequestMapping(value="/mensaje/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Mensaje: Lista");
		model.addAttribute("titulo", "Listado de mensajes");
		model.addAttribute("mensajes", service.findAll());
		return "mensaje/listar";
	}
	
	@RequestMapping(value="/mensaje/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Mensaje mensaje = new Mensaje();
		model.put("mensaje", mensaje);
		model.put("tituloWeb", "Mensaje: Crear");
		model.put("titulo", "Formulario de Mensaje");
		return "mensaje/nuevo";
	}
	
	@RequestMapping(value="/mensaje/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Mensaje> cli = null;
		
		if(id>0) {
			cli = service.findOne(id);
		}else {
			return "redirect:/mensaje/listar";
		}
		
		model.put("mensaje", cli);
		model.put("tituloWeb", "Mensaje: Editar");
		model.put("titulo", "Edicion de Mensaje");
		
		return "mensaje/nuevo";
	}
	
	@RequestMapping(value="/mensaje/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid Mensaje mensaje, BindingResult result, Model model,SessionStatus stat) {
		
		if(result.hasErrors()) {
			return "mensaje/nuevo";
		}
		
		service.save(mensaje);
		stat.setComplete();
		return "redirect:/mensaje/listar";
	}
	
	@RequestMapping(value="/mensaje/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/mensaje/listar";
	}
}
