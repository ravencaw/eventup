package es.eventup.app.controllers;

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

import es.eventup.app.models.service.UsuarioService;
import es.eventup.app.models.entity.Usuario;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="/usuario/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("tituloWeb", "Usuario: Lista");
		model.addAttribute("usuarios", service.findAll());
		return "usuario/listar";
	}
	
	@RequestMapping(value="/usuario/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("tituloWeb", "Usuario: Crear");
		model.put("titulo", "Formulario de Usuario");
		return "usuario/nuevo";
	}
	
	@RequestMapping(value="/usuario/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Usuario> usu = null;
		
		if(id>0) {
			usu = service.findOne(id);
		}else {
			return "redirect:/usuario/listar";
		}
		
		model.put("usuario", usu);
		model.put("tituloWeb", "Usuario: Editar");
		model.put("titulo", "Edicion de Usuario");
		
		return "usuario/nuevo";
	}
	
	@RequestMapping(value="/usuario/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus stat) {
		
		if(result.hasErrors()) {
			return "usuario/nuevo";
		}
		
		service.save(usuario);
		stat.setComplete();
		return "redirect:/usuario/listar";
	}
	
	@RequestMapping(value="usuario/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/usuario/listar";
	}
}
