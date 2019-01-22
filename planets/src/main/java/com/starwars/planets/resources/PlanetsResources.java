package com.starwars.planets.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starwars.planets.domain.Planet;
import com.starwars.planets.services.PlanetsService;


@RestController
@RequestMapping("/planets")
public class PlanetsResources {

	@Autowired
	private PlanetsService planetsService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Planet>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(planetsService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Planet planets) {
		planets = planetsService.salvar(planets);
		
		URI	uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
				.buildAndExpand(planets.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Planet> planets = planetsService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(planets);
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<Planet> deletar(@PathVariable("id") Long id) {
		planetsService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Planet planets, @PathVariable("id") Long id) {
		planets.setId(id);
		planets.setId(id);
		planetsService.atualizar(planets);
		
		return ResponseEntity.noContent().build();
	}
	
	}
