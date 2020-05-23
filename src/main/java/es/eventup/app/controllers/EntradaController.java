package es.eventup.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.eventup.app.models.entity.User;
import es.eventup.app.models.repository.UserRepository;
import es.eventup.app.models.service.EntradaService;

@Controller
@SessionAttributes("entrada")
public class EntradaController {
	
	@Autowired
	private EntradaService service;
	
	@Autowired
	private UserRepository userService;
	
	@RequestMapping(value="/entrada/listar/{id_venta}", method=RequestMethod.GET)
	public String listar(Model model, @PathVariable(value="id_venta") Long id_venta) {
		model.addAttribute("tituloWeb", "Entrada: Lista");
		model.addAttribute("titulo", "Listado de entradas");
		model.addAttribute("entradas", service.findAll());
		return "entrada/listar";
	}
	@RequestMapping(value="/perfil/misEntradas", method=RequestMethod.GET)
	public String misEntradas(Model model, Authentication authentication) {
		model.addAttribute("tituloWeb", "Mis Entradas");
		model.addAttribute("titulo", "Mis Entradas");
		
		UserDetails userDetails = (authentication!=null)?(UserDetails) authentication.getPrincipal():null;
		User usuario = userService.findByUsername(userDetails.getUsername()).get();
		
		model.addAttribute("entradas", service.findByUsuario(usuario.getId()));
		return "perfil/misEntradas";
	}
	@RequestMapping(value="/entrada/show/{id_evento}", method=RequestMethod.GET)
	public String show(Model model, @PathVariable(value="id_evento") Long id_evento) {
		model.addAttribute("tituloWeb", "Entrada: Show");
		model.addAttribute("entrada", service.findOne(id_evento).get());
		return "entrada/show";
	}
	
	@RequestMapping(value="/entrada/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/entrada/listar";
	}
}
