package es.eventup.app.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.entity.Transporte;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.entity.Venta;
import es.eventup.app.models.projections.EntradaProjection_Lista;
import es.eventup.app.models.projections.TransporteProjection;
import es.eventup.app.models.repository.UserRepository;
import es.eventup.app.models.service.EntradaService;
import es.eventup.app.models.service.EventoService;
import es.eventup.app.models.service.TransporteService;
import es.eventup.app.models.service.UsuarioService;
import es.eventup.app.models.service.VentaService;

@Controller
@SessionAttributes("venta")
public class VentaController {

	
	@Autowired
	private VentaService service;
	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private TransporteService transporteService;
	
	@Autowired
	private UserRepository userService;
	
	@Autowired
	private EntradaService entradaService;
	
	@RequestMapping(value="/venta/listar/{id_evento}", method=RequestMethod.GET)
	public String listar(Model model, @PathVariable(value="id_evento")Long id_evento) {
		model.addAttribute("tituloWeb", "Venta: Lista");
		model.addAttribute("titulo", "Listado de ventas");
		model.addAttribute("id_evento", id_evento);
		model.addAttribute("ventas", service.findByEvento(id_evento));
		return "venta/listar";
	}
	
	@RequestMapping(value="/venta/nuevo/{id_evento}", method=RequestMethod.GET)
	public String crear(Map<String, Object> model, @PathVariable(value="id_evento")Long id_evento) {
		Venta venta = new Venta();
		Evento evento = eventoService.findOne(id_evento).get();
		List<TransporteProjection> transportes = transporteService.findByEvento(id_evento);
		model.put("venta", venta);
		model.put("transportes", transportes);
		model.put("evento", evento);
		model.put("tituloWeb", "Venta: Crear");
		model.put("titulo", "Formulario de Venta");
		return "venta/nuevo";
	}
	
	@RequestMapping(value="/venta/edit/{id_evento}/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, @PathVariable(value="id_evento")Long id_evento) {
		
		Venta venta = null;
		
		if(id>0) {
			venta = service.findOne(id).get();
		}else {
			return "redirect:/venta/listar";
		}
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date = format.format(venta.getFecha());
		
		model.put("venta", venta);
		model.put("id_evento", id_evento);
		model.put("date", date);
		model.put("tituloWeb", "Venta: Editar");
		model.put("titulo", "Edicion de Venta");
		
		return "venta/edit";
	}
	
	@RequestMapping(value="/venta/nuevo/{id_evento}", method=RequestMethod.POST)
	public String guardar(Venta venta, BindingResult result, Model model,SessionStatus stat,@PathVariable(value="id_evento") Long id_evento, Authentication authentication, @RequestParam(name="transporte") Long id_transporte) {
		
		
		Double total;
		Transporte trans = null;
		
		Evento evento = eventoService.findOne(id_evento).get();
		if(id_transporte!=0) {
			 trans=transporteService.findOne(id_transporte).get();
		}
		
		total = evento.getPrecio();
		
		venta.prePersist();
		venta.setEvento(evento);
		if(trans!=null) {
			venta.setTotal(total+trans.getPrecio());
		}else{
			venta.setTotal(total);
		}
		
		
		service.save(venta);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		Entrada entrada = new Entrada();
		
		entrada.setVenta(venta);
		entrada.setTipo("normal");
		entrada.setNumAsiento("23");
		if(trans!=null) {
			entrada.setTransporte(trans);
		}
		entrada.setUsuario(userService.findByUsername(userDetails.getUsername()).get());
		
		entradaService.save(entrada);
		
		stat.setComplete();
		return "redirect:/perfil/misEntradas";
	}
	
	@RequestMapping(value="/venta/editar/{id_evento}/{id}", method=RequestMethod.POST)
	public String editar(Model model,SessionStatus stat,@PathVariable(value="id_evento") Long id_evento, Authentication authentication, @RequestParam(name="fecha") String fecha, @RequestParam(name="hora") String hora, @PathVariable(value="id") Long id) throws ParseException {
		
		Venta venta = service.findOne(id).get();
		
		SimpleDateFormat format_fecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha_date = format_fecha.parse(fecha);
		SimpleDateFormat format_hora = new SimpleDateFormat("HH:mm");
		Date hora_date = format_hora.parse(hora);

		venta.setFecha(fecha_date);
		venta.setHora(hora_date);
		
		service.save(venta);
		
		stat.setComplete();
		return "redirect:/venta/listar/"+id_evento;
	}
	
	@RequestMapping(value="/venta/delete/{id_evento}/{id}")
	public String eliminar(@PathVariable(value="id") Long id,@PathVariable(value="id_evento") Long id_evento) {
		
		EntradaProjection_Lista e = entradaService.findByVenta(id).get(0);
		
		if(id>0) {
			entradaService.delete(e.getId());
		}
		
		return "redirect:/venta/listar/"+id_evento;
	}
}
