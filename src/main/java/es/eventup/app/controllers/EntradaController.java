package es.eventup.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.eventup.app.models.service.EntradaService;

@Controller
@SessionAttributes("entrada")
public class EntradaController {
	
	@Autowired
	private EntradaService service;
	
	@RequestMapping(value="/entrada/listar/{id_venta}", method=RequestMethod.GET)
	public String listar(Model model, @PathVariable(value="id_venta") Long id_venta) {
		model.addAttribute("tituloWeb", "Entrada: Lista");
		model.addAttribute("titulo", "Listado de entradas");
		model.addAttribute("entradas", service.findAll());
		return "entrada/listar";
	}
	
	@RequestMapping(value="/entrada/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/entrada/listar";
	}
}
