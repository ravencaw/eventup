package es.eventup.app.controllers;

import java.util.Iterator;
import java.util.List;
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

import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.entity.Transporte;
import es.eventup.app.models.projections.TransporteProjection;
import es.eventup.app.models.service.EntradaService;
import es.eventup.app.models.service.EventoService;
import es.eventup.app.models.service.TransporteService;

@Controller
@SessionAttributes("transporte")
public class TransporteController {
	
	
	@Autowired
	private TransporteService service;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private EntradaService entradaService;
	
	
	@RequestMapping(value="/transporte/listar/{id_evento}", method=RequestMethod.GET)
	public String listar(Model model, @PathVariable(value="id_evento") Long id_evento) {
		List<TransporteProjection> transporte = service.findByEvento(id_evento);
		model.addAttribute("titulo", "Listado de Transportes");
		model.addAttribute("tituloWeb", "Transporte: Lista");
		model.addAttribute("id_evento", id_evento);
		model.addAttribute("transportes", service.findByEvento(id_evento));
		return "transporte/listar";
	}
	
	@RequestMapping(value="/transporte/nuevo/{id_evento}", method=RequestMethod.GET)
	public String crear(Map<String, Object> model, @PathVariable(value="id_evento") Long id_evento) {
		Transporte transporte = new Transporte();
		model.put("transporte", transporte);
		model.put("id_evento", id_evento);
		model.put("tituloWeb", "Registrelo");
		model.put("titulo", "Añadir Transporte");
		return "transporte/nuevo";
	}
	
	@RequestMapping(value="/transporte/form/{id_evento}/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, @PathVariable(value="id_evento") Long id_evento) {
		
		Optional<Transporte> transporte = null;
		
		if(id>0) {
			transporte = service.findOne(id);
		}else {
			return "redirect:/transporte/listar/"+id_evento;
		}
		
		model.put("transporte", transporte);
		model.put("id_evento", id_evento);
		model.put("tituloWeb", "Transporte: Editar");
		model.put("titulo", "Edicion de Transporte");
		
		return "transporte/nuevo";
	}
	
	@RequestMapping(value="/transporte/nuevo/{id_evento}", method=RequestMethod.POST)
	public String guardar(@Valid Transporte transporte, BindingResult result, Model model, SessionStatus stat, @PathVariable(value="id_evento") Long id_evento) {

		if(result.hasErrors()) {
			return "transporte/nuevo/"+id_evento;
		}
		
		Evento e = eventoService.findOne(id_evento).get();
		
		transporte.setEvento(e);
		
		service.save(transporte);
		stat.setComplete();
		return "redirect:/transporte/listar/"+id_evento;
		
	}
	
	@RequestMapping(value="transporte/delete/{id_evento}/{id}")
	public String eliminar(@PathVariable(value="id") Long id, @PathVariable(value="id_evento") Long id_evento) {
		
		List<Entrada> listEntradas = entradaService.findByTransporte(id);
		
		Iterator<Entrada> it = listEntradas.iterator();
		
		while(it.hasNext()) {
			Entrada e = it.next();
			
			e.setTransporte(null);
			
			entradaService.save(e);
		}
		
		if(id>0) {
			service.delete(id);
		}
		
		
		return "redirect:/transporte/listar/"+id_evento;
	}
}