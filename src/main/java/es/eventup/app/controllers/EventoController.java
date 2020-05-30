package es.eventup.app.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.entity.EventoFormulario;
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

	@RequestMapping(value = "/evento/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Evento: Lista");
		model.addAttribute("titulo", "Listado de eventos");
		model.addAttribute("eventos", service.findAll());
		return "evento/listar";
	}

	@RequestMapping(value = "/evento/busqueda", method = RequestMethod.GET)
	public String busqueda(Model model, Authentication authentication) {

		UserDetails userDetails = (authentication != null) ? (UserDetails) authentication.getPrincipal() : null;

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

	@RequestMapping(value = "/evento/show/{id}", method = RequestMethod.GET)
	public String show(Model model, @PathVariable(name = "id") Long id, Authentication authentication) {

		UserDetails userDetails = (authentication != null) ? (UserDetails) authentication.getPrincipal() : null;
		
		Evento e = service.findOne(id).get();
		User usuario;
		
		boolean evento_pasado = false;
		
		Date fecha_actual = new Date();
		
		if(e.getFecha().compareTo(fecha_actual)<0) {
			evento_pasado = true;
		}

		if (userDetails != null)
			usuario = userService.findByUsername(userDetails.getUsername()).get();
		else
			usuario = null;

		model.addAttribute("tituloWeb", "Evento: Show");
		model.addAttribute("titulo", "Datos del evento");
		model.addAttribute("evento_pasado", evento_pasado);
		model.addAttribute("evento", e);
		model.addAttribute("user", usuario);
		return "evento/show";
	}

	@RequestMapping(value = "/perfil/misEventos", method = RequestMethod.GET)
	public String calendar(Model model) {
		model.addAttribute("tituloWeb", "Evento: Calendario");
		model.addAttribute("titulo", "Calendario eventos");
		return "perfil/misEventos";
	}

	@RequestMapping(value = "/evento/nuevo", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Evento evento = new Evento();
		model.put("evento", evento);
		model.put("tituloWeb", "Evento: Crear");
		model.put("titulo", "Formulario de Evento");
		return "evento/nuevo";
	}

	@RequestMapping(value = "/evento/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Optional<Evento> cli = null;

		if (id > 0) {
			cli = service.findOne(id);
		} else {
			return "redirect:/evento/listar";
		}

		model.put("evento", cli);
		model.put("tituloWeb", "Evento: Editar");
		model.put("titulo", "Edicion de Evento");

		return "evento/nuevo";
	}

	@RequestMapping(value = "/evento/nuevo", method = RequestMethod.POST)
	public String guardar(@Valid EventoFormulario evento, BindingResult result, Model model,
			Authentication authentication, @RequestParam("foto") MultipartFile foto) {

		if (result.hasErrors()) {
			return "evento/nuevo";
		}

		if (!foto.isEmpty()) {
			Path directorioFotos = Paths.get(".//src//main//resources//static//images//eventos");
			// String rootPath = directorioFotos.toFile().getAbsolutePath(); PETABA
			String uuid = UUID.randomUUID().toString();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			File newFile = new File(directorioFotos.toString()+"/" + uuid + "." + foto.getContentType().split("/")[1]);

			try {
				inputStream = foto.getInputStream();

				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			String username = authentication.getName();
			User session = userService.findByUsername(username).get();
			Evento eventoEntity = new Evento();
			eventoEntity.setNombre(evento.getNombre());
			eventoEntity.setDescripcion(evento.getDescripcion());
			eventoEntity.setFoto("/images/eventos/" + uuid + "." + foto.getContentType().split("/")[1]);
			eventoEntity.setOrganizador(evento.getOrganizador());
			eventoEntity.setCiudad(evento.getCiudad());
			eventoEntity.setDireccion(evento.getDireccion());
			eventoEntity.setFecha(evento.getFecha());
			eventoEntity.setHora(evento.getHora());
			eventoEntity.setPrecio(evento.getPrecio());
			eventoEntity.setCantidadEntradas(evento.getCantidadEntradas());
			eventoEntity.setLatitud(evento.getLatitud());
			eventoEntity.setLongitud(evento.getLongitud());
			eventoEntity.setUsuario(session);

			service.save(eventoEntity);
			return "redirect:/evento/listar";
		}

//		
//
//		User usuario = userService.findByUsername(userDetails.getUsername()).get();
//
//		evento.setUsuario(usuario);
//		evento.setPrecio(Double.parseDouble(precio));
//
//		service.save(evento);
//		stat.setComplete();
		ObjectError errorFoto = new ObjectError("foto", "Error al subir la foto");
		result.addError(errorFoto);
		return "evento/nuevo";
	}

	@RequestMapping(value = "/evento/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			service.delete(id);
		}

		return "redirect:/evento/listar";
	}
}
