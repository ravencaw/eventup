package es.eventup.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.repository.UserRepository;
import es.eventup.app.models.service.EntradaService;
import es.eventup.app.models.service.VentaService;

@Controller
@SessionAttributes("entrada")
public class EntradaController {
	
	@Autowired
	private EntradaService service;
	
	@Autowired
	private UserRepository userService;
	
	@Autowired
	private VentaService ventaService;
	
	@RequestMapping(value="/entrada/listar/{id_evento}/{id_venta}", method=RequestMethod.GET)
	public String listar(Model model, @PathVariable(value="id_venta") Long id_venta) {
		model.addAttribute("tituloWeb", "Entrada: Lista");
		model.addAttribute("id_venta",id_venta);
		model.addAttribute("titulo", "Listado de entradas");
		model.addAttribute("entradas", service.findByVenta(id_venta));
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
	
	@RequestMapping(value="/entrada/delete/{id_venta}/{id}")
	public String eliminar(@PathVariable(value="id") Long id, @PathVariable(value="id_venta")Long id_venta) {
		
		Long id_evento = ventaService.findOne(id_venta).get().getEvento().getId();
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/venta/listar/"+id_evento;
	}
	
	@RequestMapping(value="/entrada/form/{id_venta}/{id}")
	public String editEntrada(@PathVariable(value="id") Long id, Map<String, Object> model, @PathVariable(value="id_venta")Long id_venta) {
		
		Entrada entrada = null;
		
		if(id>0) {
			entrada = service.findOne(id).get();
		}else {
			return "redirect:/entrada/listar/"+id_venta;
		}
		
		
		model.put("entrada", entrada);
		model.put("id_venta", id_venta);
		model.put("tituloWeb", "Entrada: Editar");
		model.put("titulo", "Edicion de Entrada");
		
		return "entrada/edit";
	}
	
	@RequestMapping(value="/entrada/edit/{id_venta}/{id}")
	public String editar(@PathVariable(value="id") Long id, @PathVariable(value="id_venta") Long id_venta, @RequestParam(name="num_asiento") String num_asiento, @RequestParam(name="tipo") String tipo) {
		
		Entrada entrada = service.findOne(id).get();
		
		entrada.setNumAsiento(num_asiento);
		entrada.setTipo(tipo);
		
		service.save(entrada);
		
		return "redirect:/entrada/listar/"+id_venta+"/"+id;
	}
}
