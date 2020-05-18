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


import es.eventup.app.models.entity.Blog;
import es.eventup.app.models.service.BlogService;

@Controller
@SessionAttributes("blog")
public class BlogController {

	
	@Autowired
	private BlogService service;
	
	@RequestMapping(value="/blog/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("tituloWeb", "Blog: Lista");
		model.addAttribute("titulo", "Listado de blogs");
		model.addAttribute("blogs", service.findAll());
		return "blog/listar";
	}
	
	@RequestMapping(value="/blog/nuevo", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Blog blog = new Blog();
		model.put("blog", blog);
		model.put("tituloWeb", "Blog: Crear");
		model.put("titulo", "Formulario de Blog");
		return "blog/nuevo";
	}
	
	@RequestMapping(value="/blog/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Blog> cli = null;
		
		if(id>0) {
			cli = service.findOne(id);
		}else {
			return "redirect:/blog/listar";
		}
		
		model.put("blog", cli);
		model.put("tituloWeb", "Blog: Editar");
		model.put("titulo", "Edicion de Blog");
		
		return "blog/nuevo";
	}
	
	@RequestMapping(value="/blog/nuevo", method=RequestMethod.POST)
	public String guardar(@Valid Blog blog, BindingResult result, Model model,SessionStatus stat) {
		
		if(result.hasErrors()) {
			return "blog/nuevo";
		}
		
		service.save(blog);
		stat.setComplete();
		return "redirect:/blog/listar";
	}
	
	@RequestMapping(value="/blog/delete/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			service.delete(id);
		}
		
		return "redirect:/blog/listar";
	}
}
