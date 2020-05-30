package es.eventup.app.controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.eventup.app.models.service.AuthorityService;
import es.eventup.app.models.service.UsuarioService;
import es.eventup.app.models.entity.Authority;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.repository.UserRepository;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private UserRepository userService;
	
	@RequestMapping(value="/usuario/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("tituloWeb", "Usuario: Lista");
		model.addAttribute("usuarios", service.findAll());
		return "usuario/listar";
	}
	
	@RequestMapping(value="/usuario/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		User usuario = new User();
		model.put("usuario", usuario);
		model.put("tituloWeb", "Registrate");
		model.put("titulo", "Formulario de User");
		return "usuario/nuevo";
	}
	
	@RequestMapping(value="/usuario/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<User> usu = null;
		
		if(id>0) {
			usu = service.findOne(id);
		}else {
			return "redirect:/usuario/listar";
		}
		
		model.put("usuario", usu);
		model.put("tituloWeb", "User: Editar");
		model.put("titulo", "Edicion de User");
		
		return "usuario/nuevo";
	}
	@RequestMapping(value="/perfil/miPerfil")
	public String miPerfil(Map<String, Object> model, Authentication authentication) {
		
		UserDetails userDetails = (authentication!=null)?(UserDetails) authentication.getPrincipal():null;
		User usuario = userService.findByUsername(userDetails.getUsername()).get();
		
		model.put("usuario", usuario);
		model.put("tituloWeb", "User: Editar");
		model.put("titulo", "Edicion de User");
		
		return "/perfil/miPerfil";
	}
	
	@RequestMapping(value="/usuario/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid User usuario, BindingResult result, Model model, SessionStatus stat) {
		
		Set<Authority> authoritySet=new HashSet<Authority>();
		
		if(result.hasErrors()) {
			return "usuario/nuevo";
		}
		
		authoritySet.add(authorityService.findOne(Long.parseLong("2")).get());
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		String pass = bCryptPasswordEncoder.encode(usuario.getPassword());
		
		usuario.setAuthority(authoritySet);
		usuario.setEnabled(true);
		usuario.setPassword(pass);
		
		service.save(usuario);
		
		stat.setComplete();
		return "/perfil/miPerfil";
	}
	
	@RequestMapping(value="/usuario/editar", method=RequestMethod.POST)
	public String editar(Model model, SessionStatus stat, Authentication authentication, 
			@RequestParam(name="nombre") String nombre, @RequestParam(name="apellidos") String apellidos, @RequestParam(name="email") String email,
			@RequestParam(name="localidad") String localidad, @RequestParam(name="provincia") String provincia, @RequestParam(name="password") String password) {
		
		UserDetails userDetails = (authentication!=null)?(UserDetails) authentication.getPrincipal():null;
		User usuario = userService.findByUsername(userDetails.getUsername()).get();
		
		if(nombre!=null && nombre!="") {
			usuario.setNombre(nombre);
		}
		if(apellidos!=null && apellidos!="") {
			usuario.setApellidos(apellidos);
		}
		if(email!=null && email!="") {
			usuario.setEmail(email);
		}
		if(localidad!=null && localidad!="") {
			usuario.setLocalidad(localidad);
		}
		if(provincia!=null && provincia!="") {
			usuario.setProvincia(provincia);
		}
		
		if(password!=null && password!="") {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
			String pass = bCryptPasswordEncoder.encode(password);
			
			usuario.setPassword(pass);
		}
		
		service.save(usuario);
		
		stat.setComplete();
		return "redirect:/perfil/miPerfil";
	}
	
	@RequestMapping(value="usuario/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/usuario/listar";
	}
}
