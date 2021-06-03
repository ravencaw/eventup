package es.eventup.app.controllers;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.eventup.app.models.entity.Valoracion;
import es.eventup.app.models.service.BlogService;
import es.eventup.app.models.service.UsuarioService;
import es.eventup.app.models.service.ValoracionService;

@Controller
@SessionAttributes("valoracion")
public class ValoracionController {

	private final ValoracionService service;
	private final BlogService blogService;
	private final UsuarioService usuarioService;

	public ValoracionController(ValoracionService service, BlogService blogService, UsuarioService usuarioService) {
		super();
		this.service = service;
		this.blogService = blogService;
		this.usuarioService = usuarioService;
	}

	@RequestMapping(value = "/valoracion/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Valoraciones");
		model.addAttribute("tituloWeb", "Valoracion: Lista");
		model.addAttribute("valoraciones", service.findAll());
		return "valoracion/listar";
	}

	@RequestMapping(value = "/valoracion/nuevo/{id_blog}", method = RequestMethod.GET)
	public String crear(@PathVariable(name = "id_blog") Long id_blog, Map<String, Object> model,
			Authentication authentication, HttpServletRequest request) {
		
		Valoracion valoracion = new Valoracion();
		
		model.put("id_blog", id_blog);
		model.put("valoracion", valoracion);
		model.put("tituloWeb", "Añade");
		model.put("titulo", "Nueva Valoracion");

		return "valoracion/nuevo";
	}

	@RequestMapping(value = "/valoracion/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Optional<Valoracion> valoracion = null;

		if (id > 0) {
			valoracion = service.findOne(id);
		}

		model.put("valoracion", valoracion);
		model.put("tituloWeb", "Valoracion: Editar");
		model.put("titulo", "Edicion de Valoracion");

		return "valoracion/nuevo";
	}

	@RequestMapping(value = "/valoracion/nuevo/{id_blog}", method = RequestMethod.POST)
	public String guardar(@PathVariable(name = "id_blog") Long id_blog, @Valid Valoracion valoracion, BindingResult result, Model model, SessionStatus stat, Authentication authentication) {

		valoracion.setBlog(blogService.findOne(id_blog).get());
		valoracion.setUser(usuarioService.findByUsername(authentication.getName()).get());
		
//		if(result.hasErrors()) {
//			return "redirect:/valoracion/nuevo/"+id_blog;
//		}

		service.save(valoracion);
		stat.setComplete();
		return "redirect:/blog/show/"+valoracion.getBlog().getId();

	}

	@RequestMapping(value = "valoracion/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		Long redi = service.findOne(id).get().getBlog().getId();

		if (id > 0) {
			service.delete(id);
		}

		return "redirect:/blog/show/" + redi;
	}
}