package es.eventup.app.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
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
import es.eventup.app.models.projections.UserProjection;
import es.eventup.app.models.repository.UserRepository;
import es.eventup.app.models.service.EventoService;
import es.eventup.app.models.service.UsuarioService;
import es.eventup.app.util.mail.MailSend;

@Controller
@SessionAttributes("evento")
public class EventoController {

	private final EventoService service;

	private final UsuarioService usuarioService;
	private final MailSend mailSend;

	public EventoController(MailSend mailSend, EventoService service, UsuarioService usuarioService) {
		this.mailSend = mailSend;
		this.service = service;
		this.usuarioService = usuarioService;
	}

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
			usuario = usuarioService.findByUsername(userDetails.getUsername()).get();
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

		User usuario;

		if (userDetails != null)
			usuario = usuarioService.findByUsername(userDetails.getUsername()).get();
		else
			usuario = null;

		model.addAttribute("tituloWeb", "Evento: Show");
		model.addAttribute("titulo", "Datos del evento");
		model.addAttribute("evento", service.findOne(id).get());
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
		model.put("titulo", "Nuevo Evento");
		return "evento/nuevo";
	}

	@RequestMapping(value = "/evento/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Optional<Evento> cli = Optional.empty();

		if (id > 0) {
			cli = service.findOne(id);
		} else {
			return "redirect:/evento/listar";
		}

		model.put("evento", cli.get());
		model.put("tituloWeb", "Evento: Editar");
		model.put("titulo", "Edicion de Evento");

		return "evento/nuevo";
	}

	@RequestMapping(value = "/evento/nuevo", method = RequestMethod.POST)
	public String guardar(@Valid EventoFormulario evento, BindingResult result, Model model,
			Authentication authentication, @RequestParam("foto") MultipartFile foto, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "evento/nuevo";
		}

		if (!foto.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			try {

				saveImageFile(foto, null, request);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String username = authentication.getName();
			User session = usuarioService.findByUsername(username).orElseGet(null);
			Evento eventoEntity = new Evento();
			eventoEntity.setNombre(evento.getNombre());
			eventoEntity.setDescripcion(evento.getDescripcion());
			eventoEntity.setFoto(uuid + "." + foto.getContentType().split("/")[1]);
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

			List<UserProjection> userProvincias = usuarioService.porProvincias(eventoEntity.getCiudad());
			String[] cco = userProvincias.stream().map(u -> u.getEmail()).collect(Collectors.toList())
					.toArray(new String[userProvincias.size()]);
			String sujeto = "EVENTUP-EVENTO CREADO EN TU ZONA";
			String contenido = "Se ha creado un evento en tu zona. Accede a nuestra aplicacion para verlo. Muchas gracias";
			mailSend.sendEmailCCO(cco, sujeto, contenido);

			return "redirect:/evento/listar";
		}

		ObjectError errorFoto = new ObjectError("foto", "Error al subir la foto");
		result.addError(errorFoto);
		return "evento/nuevo";
	}

	public static void saveImageFile(MultipartFile foto, String name, HttpServletRequest request) throws IOException {

		File dir = new File(".//src//main//resources//static//images//eventos");

		String uuid = UUID.randomUUID().toString();

		if (!dir.exists()) {
			dir.mkdirs();
		}

		File serverFile = new File(dir + "/" + uuid + "." + foto.getContentType().split("/")[1]);
		BufferedOutputStream stream;

		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(foto.getBytes());
		stream.close();

	}

	@RequestMapping(value = "/evento/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			service.delete(id);
		}

		return "redirect:/evento/listar";
	}
}
