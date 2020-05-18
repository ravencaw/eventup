package es.eventup.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.projections.EntradaProjection;
import es.eventup.app.models.projections.EventoProjection;
import es.eventup.app.models.service.EntradaService;
import es.eventup.app.models.service.EventoService;

@RestController
@RequestMapping(path = "/rest")
public class GlobalRestController {
	
	@Autowired
	EntradaService service;
	@Autowired
	EventoService eventoService;
	
	@GetMapping(path = "asistencia/{id}/{dni}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntradaProjection> get(@PathVariable("dni") String dni, @PathVariable("id") Long id) {
        Optional<EntradaProjection> res = service.findByUserAndEvento(dni, id);
        assert res.orElse(null) != null;
        return ResponseEntity.ok(res.orElse(null));
    }
	@GetMapping(path = "evento/calendar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<EventoProjection> get() {
		List<EventoProjection> res = eventoService.findAllProjections();
		return res;
	}
}
