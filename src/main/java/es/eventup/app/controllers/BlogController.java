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

import es.eventup.app.models.entity.Blog;
import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.service.BlogService;
import es.eventup.app.models.service.EventoService;
import es.eventup.app.models.service.UsuarioService;

@Controller
@SessionAttributes("blog")
public class BlogController {

	private final UsuarioService usuarioService;
	private final BlogService blogService;
	private final EventoService eventoService;

	public BlogController(UsuarioService usuarioService, BlogService blogService, EventoService eventoService) {
		super();
		this.usuarioService = usuarioService;
		this.blogService = blogService;
		this.eventoService = eventoService;
	}

	// LISTO LISTAR
	/*
	 * QUITADO
	 * 
	 * @RequestMapping(value="/blog/listar", method=RequestMethod.GET) public String
	 * listar(Model model) { model.addAttribute("tituloWeb", "Blog: Lista");
	 * model.addAttribute("titulo", "Listado de blogs"); model.addAttribute("blog",
	 * blogService.findAll()); return "blog/listar"; }
	 */

	@RequestMapping(value = "/blog/nuevo/{id_evento}", method = RequestMethod.GET)
	public String crear(@PathVariable(name = "id_evento") Long id_evento, Map<String, Object> model) {
		Blog blog = new Blog();
		
		model.put("blog", blog);
		model.put("id_evento", id_evento);
		model.put("tituloWeb", "Blog: Crear");
		model.put("titulo", "Añadir Blog");
		// return "blog/show/{id}";
		return "blog/nuevo";
	}

	@RequestMapping(value = "/blog/nuevo/{id_evento}", method = RequestMethod.POST)
	public String guardar(@PathVariable(name = "id_evento") Long id_evento, @Valid Blog blog, BindingResult result,
			Model model, SessionStatus stat, @RequestParam("fotos") MultipartFile fotos,  @RequestParam("videos") MultipartFile videos) {

		blog.setEvento(eventoService.findOne(id_evento).get());
		Evento e = eventoService.findOne(id_evento).get();
		e.setBlog(blog);
		
//		if (result.hasErrors()) {
//			return "redirect:/blog/nuevo/"+id_evento;
//		}
//		
		if (!fotos.isEmpty()) {
			Path directorioFotos = Paths.get(".//src//main//resources//static//images//blogs");
			// String rootPath = directorioFotos.toFile().getAbsolutePath(); PETABA
			String uuid = UUID.randomUUID().toString();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			File newFile = new File(directorioFotos.toString()+"/" + uuid + "." + fotos.getContentType().split("/")[1]);

			try {
				inputStream = fotos.getInputStream();

				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
			} catch (IOException a) {
				a.printStackTrace();
			}
		
		blog.setFotos(uuid + "." + fotos.getContentType().split("/")[1]);
		}
		if (!videos.isEmpty()) {
			Path directoriovideos = Paths.get(".//src//main//resources//static//videos");
			// String rootPath = directorioFotos.toFile().getAbsolutePath(); PETABA
			String uuid = UUID.randomUUID().toString();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			File newFile = new File(directoriovideos.toString()+"/" + uuid + "." + videos.getContentType().split("/")[1]);

			try {
				inputStream = videos.getInputStream();

				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
			} catch (IOException a) {
				a.printStackTrace();
			}
		
		blog.setVideos(uuid + "." + videos.getContentType().split("/")[1]);
		}
		blogService.save(blog);
		eventoService.save(e);
		stat.setComplete();
		// return "redirect:/blog/show/"+blog.getId();
		return "redirect:/blog/show/" + blog.getId();
	}

	@RequestMapping(value = "/blog/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Blog cli = null;

		if (id > 0) {
			cli = blogService.findOne(id).get();
		}
		
		model.put("id_evento" , cli.getEvento().getId());
		model.put("blog", cli);
		model.put("tituloWeb", "Blog: Editar");
		model.put("titulo", "Edicion de Blog");

		return "blog/nuevo";
	}

	@RequestMapping(value = "/blog/show/{id}", method = RequestMethod.GET)
	public String show(Model model, @PathVariable(name = "id") Long id, Authentication authentication) {

		UserDetails userDetails = (authentication != null) ? (UserDetails) authentication.getPrincipal() : null;

		Blog b = blogService.findOne(id).get();
		User usuario;
		
		
		if (userDetails != null)
			usuario = usuarioService.findByUsername(userDetails.getUsername()).get();

		else
			usuario = null;

		model.addAttribute("tituloWeb", "Blog: Show");
		model.addAttribute("titulo", "Datos del Blog");
		model.addAttribute("blog", b);
		model.addAttribute("user", usuario);
		return "blog/show";
	}

	@RequestMapping(value = "/blog/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		Blog b = new Blog();
		b = blogService.findOne(id).get();
		Evento e = eventoService.findOne(b.getEvento().getId()).get();
		Long redi = b.getEvento().getId();

		if (id > 0) {
			blogService.delete(id);
			e.setBlog(null);
		}
		eventoService.save(e);
		return "redirect:/evento/show/" + redi;
	}

}
