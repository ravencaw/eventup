package es.eventup.app.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Home");
		model.addAttribute("titulo", "Home");
		return "/home";
	}
}
