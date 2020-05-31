package es.eventup.app.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.eventup.app.models.service.EventoService;
import es.eventup.app.models.service.UsuarioService;
import es.eventup.app.util.mail.MailSend;

@Controller
@SessionAttributes("evento")

public class HomeController {
	
	private final EventoService serviceEvento;
	
	public HomeController( EventoService serviceEvento) {

		this.serviceEvento = serviceEvento;
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Home");
		model.addAttribute("titulo", "Home");
		model.addAttribute("eventos", serviceEvento.findAll());
		return "home";
	}
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String about() {
		
		return "contacto/about";
	}
	
	@RequestMapping(value="/enviar", method=RequestMethod.GET)
	public String enviar() {
		
		return "contacto/enviar";
	}
}
