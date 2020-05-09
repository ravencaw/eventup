package es.eventup.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.entity.Venta;
import es.eventup.app.models.service.EventoService;
import es.eventup.app.models.service.VentaService;

@Controller
@SessionAttributes("venta")
public class VentaController {

	
	@Autowired
	private VentaService service;
	
	@Autowired
	private EventoService eventoService;
	
	@RequestMapping(value="/venta/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Venta: Lista");
		model.addAttribute("titulo", "Listado de ventas");
		model.addAttribute("ventas", service.findAll());
		return "venta/listar";
	}
	
	@RequestMapping(value="/venta/nuevo/{id_evento}", method=RequestMethod.GET)
	public String crear(Map<String, Object> model, @PathVariable(value="id_evento")Long id_evento) {
		Venta venta = new Venta();
		Evento evento = eventoService.findOne(id_evento).get();
		model.put("venta", venta);
		model.put("evento", evento);
		model.put("tituloWeb", "Venta: Crear");
		model.put("titulo", "Formulario de Venta");
		return "venta/nuevo";
	}
	
//	@RequestMapping(value="/venta/form/{id_evento}/{id}")
//	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
//		
//		Optional<Venta> venta = null;
//		
//		if(id>0) {
//			venta = service.findOne(id);
//		}else {
//			return "redirect:/venta/listar";
//		}
//		
//		model.put("venta", venta);
//		model.put("tituloWeb", "Venta: Editar");
//		model.put("titulo", "Edicion de Venta");
//		
//		return "venta/nuevo";
//	}
	
	@RequestMapping(value="/venta/nuevo/{id_evento}", method=RequestMethod.POST)
	public String guardar(Venta venta, BindingResult result, Model model,SessionStatus stat,@PathVariable(value="id_evento") Long id_evento) {
		
		if(venta.getCantidadEntradas()==null) {
			return "redirect:/venta/nuevo/{id_evento}";
		}
		
		Double total;
		
		Evento evento = eventoService.findOne(id_evento).get();
		
		total = evento.getPrecio()*venta.getCantidadEntradas();
		
		venta.prePersist();
		venta.setIdEvento(id_evento);
		venta.setTotal(total);
		
		
		service.save(venta);
		stat.setComplete();
		return "redirect:/venta/listar";
	}
	
	@RequestMapping(value="/venta/delete/{id_evento}/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/venta/listar";
	}
}
