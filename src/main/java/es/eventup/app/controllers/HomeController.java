package es.eventup.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.projections.EventoProjection;
import es.eventup.app.models.service.EventoService;

@Controller
public class HomeController {
	
	@Autowired
	EventoService eventoService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String listar(Model model) {
		
		List<EventoProjection> eventos = eventoService.findAllLimited();
		
		model.addAttribute("eventos", eventos);
		model.addAttribute("tituloWeb", "Home");
		model.addAttribute("titulo", "Home");
		return "home";
	}
}
