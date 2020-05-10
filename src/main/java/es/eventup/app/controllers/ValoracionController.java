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

import es.eventup.app.models.entity.Valoracion;
import es.eventup.app.models.service.ValoracionService;



@Controller
@SessionAttributes("valoracion")
public class ValoracionController {
	

	
	@Autowired
	private ValoracionService service;
	
	
	@RequestMapping(value="/valoracion/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Valoraciones");
		model.addAttribute("tituloWeb", "Valoracion: Lista");
		model.addAttribute("valoraciones", service.findAll());
		return "valoracion/listar";
	}
	
	@RequestMapping(value="/valoracion/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Valoracion valoracion = new Valoracion();
		model.put("valoracion", valoracion);
		model.put("tituloWeb", "Registrelo");
		model.put("titulo", "Formulario de Valoracion");
		return "valoracion/nuevo";
	}
	
	@RequestMapping(value="/valoracion/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Valoracion> valoracion = null;
		
		if(id>0) {
			valoracion = service.findOne(id);
		}else {
			return "redirect:/valoracion/listar";
		}
		
		model.put("valoracion", valoracion);
		model.put("tituloWeb", "Valoracion: Editar");
		model.put("titulo", "Edicion de Valoracion");
		
		return "valoracion/nuevo";
	}
	
	@RequestMapping(value="/valoracion/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid Valoracion valoracion, BindingResult result, Model model, SessionStatus stat) {

		if(result.hasErrors()) {
			return "valoracion/nuevo";
		}
		
		service.save(valoracion);
		stat.setComplete();
		return "redirect:/valoracion/listar";
		
	}
	
	@RequestMapping(value="valoracion/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/valoracion/listar";
	}
}