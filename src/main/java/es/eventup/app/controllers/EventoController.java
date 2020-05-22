package es.eventup.app.controllers;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.repository.UserRepository;
import es.eventup.app.models.service.EventoService;

@Controller
@SessionAttributes("evento")
public class EventoController {

	@Autowired
	private EventoService service;
	
	@Autowired
	private UserRepository userService;
	
	@RequestMapping(value="/evento/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Evento: Lista");
		model.addAttribute("titulo", "Listado de eventos");
		model.addAttribute("eventos", service.findAll());
		return "evento/listar";
	}
	@RequestMapping(value="/evento/busqueda", method=RequestMethod.GET)
	public String busqueda(Model model, Authentication authentication) {
		
		UserDetails userDetails = (authentication!=null)?(UserDetails) authentication.getPrincipal():null;
		
		User usuario;
		
		if (userDetails != null)
			usuario = userService.findByUsername(userDetails.getUsername()).get();
		else
			usuario = null;
		
		model.addAttribute("tituloWeb", "Evento: Busqueda");
		model.addAttribute("eventos", service.findAll());
		model.addAttribute("user", usuario);
		return "evento/busqueda";
	}
	@RequestMapping(value="/evento/show/{id}", method=RequestMethod.GET)
	public String show(Model model, @PathVariable(name="id") Long id, Authentication authentication) {
		
		UserDetails userDetails = (authentication!=null)?(UserDetails) authentication.getPrincipal():null;
		
		User usuario;
		
		if (userDetails != null)
			usuario = userService.findByUsername(userDetails.getUsername()).get();
		else
			usuario = null;
		
		model.addAttribute("tituloWeb", "Evento: Show");
		model.addAttribute("titulo", "Datos del evento");
		model.addAttribute("evento", service.findOne(id).get());
		model.addAttribute("user", usuario);
		return "evento/show";
	}
	@RequestMapping(value="/perfil/misEventos", method=RequestMethod.GET)
	public String calendar(Model model) {
		model.addAttribute("tituloWeb", "Evento: Calendario");
		model.addAttribute("titulo", "Calendario eventos");
		return "perfil/misEventos";
	}
	
	@RequestMapping(value="/evento/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Evento evento = new Evento();
		model.put("evento", evento);
		model.put("tituloWeb", "Evento: Crear");
		model.put("titulo", "Formulario de Evento");
		return "evento/nuevo";
	}
	
	@RequestMapping(value="/evento/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Evento> cli = null;
		
		if(id>0) {
			cli = service.findOne(id);
		}else {
			return "redirect:/evento/listar";
		}
		
		model.put("evento", cli);
		model.put("tituloWeb", "Evento: Editar");
		model.put("titulo", "Edicion de Evento");
		
		return "evento/nuevo";
	}
	
	@RequestMapping(value="/evento/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid Evento evento, BindingResult result, Model model, @RequestParam("foto") MultipartFile foto, @RequestParam("precio") String precio, SessionStatus stat, Authentication authentication) {
		
		
		if(result.hasErrors()) {
			return "evento/nuevo";
		}
		
		if(!foto.isEmpty()) {
			Path directorioFotos = Paths.get("src//main//resources//static/images/eventos");
			String rootPath = directorioFotos.toFile().getAbsolutePath();
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				
				evento.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		UserDetails userDetails = (authentication!=null)?(UserDetails) authentication.getPrincipal():null;
		
		User usuario = userService.findByUsername(userDetails.getUsername()).get();
		
		evento.setUsuario(usuario);
		evento.setPrecio(Double.parseDouble(precio));
		
		service.save(evento);
		stat.setComplete();
		return "redirect:/evento/listar";
	}
	
	@RequestMapping(value="/evento/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/evento/listar";
	}
}
