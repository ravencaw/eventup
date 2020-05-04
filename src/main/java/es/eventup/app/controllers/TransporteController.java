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

import es.eventup.app.models.entity.Transporte;
import es.eventup.app.models.service.TransporteService;

@Controller
@SessionAttributes("transporte")
public class TransporteController {
	
	
	@Autowired
	private TransporteService service;
	
	
	@RequestMapping(value="/transporte/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Transportes");
		model.addAttribute("tituloWeb", "Transporte: Lista");
		model.addAttribute("transportes", service.findAll());
		return "transporte/listar";
	}
	
	@RequestMapping(value="/transporte/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Transporte transporte = new Transporte();
		model.put("transporte", transporte);
		model.put("tituloWeb", "Registrelo");
		model.put("titulo", "Formulario de Transporte");
		return "transporte/nuevo";
	}
	
	@RequestMapping(value="/transporte/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Transporte> transporte = null;
		
		if(id>0) {
			transporte = service.findOne(id);
		}else {
			return "redirect:/transporte/listar";
		}
		
		model.put("transporte", transporte);
		model.put("tituloWeb", "Transporte: Editar");
		model.put("titulo", "Edicion de Transporte");
		
		return "transporte/nuevo";
	}
	
	@RequestMapping(value="/transporte/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid Transporte transporte, BindingResult result, Model model, SessionStatus stat) {

		if(result.hasErrors()) {
			return "transporte/nuevo";
		}
		
		service.save(transporte);
		stat.setComplete();
		return "redirect:/transporte/listar";
		
	}
	
	@RequestMapping(value="transporte/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/transporte/listar";
	}
}