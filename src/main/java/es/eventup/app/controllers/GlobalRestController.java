package es.eventup.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eventup.app.models.entity.Transporte;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.projections.EntradaProjection;
import es.eventup.app.models.projections.EventoProjection;
import es.eventup.app.models.projections.TransporteProjection;
import es.eventup.app.models.repository.UserRepository;
import es.eventup.app.models.service.EntradaService;
import es.eventup.app.models.service.EventoService;
import es.eventup.app.models.service.TransporteService;

@RestController
@RequestMapping(path = "/rest")
public class GlobalRestController {
	
	@Autowired
	private EntradaService service;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private UserRepository userService;
	@Autowired
	private TransporteService transporteService;
	
	
	
	@GetMapping(path = "venta/getTransporte/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransporteProjection> getTransporte(@PathVariable("id") Long id) {
		Optional<TransporteProjection> res = transporteService.find(id);
		assert res.orElse(null) != null;
		return ResponseEntity.ok(res.orElse(null));
	}
	@GetMapping(path = "asistencia/{id}/{dni}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntradaProjection> get(@PathVariable("dni") String dni, @PathVariable("id") Long id) {
        Optional<EntradaProjection> res = service.findByUserAndEvento(dni, id);
        assert res.orElse(null) != null;
        return ResponseEntity.ok(res.orElse(null));
    }
	@GetMapping(path = "evento/calendar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<EventoProjection> get(Authentication authentication) {
		
		UserDetails userDetails = (authentication!=null)?(UserDetails) authentication.getPrincipal():null;
		
		User usuario = userService.findByUsername(userDetails.getUsername()).get();
		
		List<EventoProjection> res = eventoService.findByUser(usuario.getId());
		return res;
	}
	@GetMapping(path = "evento/busqueda/{nombre}/{ciudad}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<EventoProjection> busqueda(@PathVariable("nombre") String nombre, @PathVariable("ciudad") String ciudad) {
		
		if(nombre.equals("null") && !ciudad.equals("null")) {
			return eventoService.findByCiudad(ciudad);
		}else if(ciudad.equals("null") && !nombre.equals("null")) {
			return eventoService.findByNombre(nombre);
		}else if(nombre.equals("null") && ciudad.equals("null")){
			return eventoService.findAllProjections();
		}else {
			return eventoService.findByNombreAndCiudad(nombre, ciudad);
		}
		
	}
}
