package com.starwars.planets.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.starwars.planets.DAO.PlanetsDAO;
import com.starwars.planets.domain.Planet;
import com.starwars.planets.services.exceptions.PlanetNaoEncontradoException;

@Service
public class PlanetsService {

	@Autowired
	private PlanetsDAO planetsDao;
	
	public List<Planet> listar(){
		return planetsDao.findAll();
	}
	
	public Optional<Planet> buscar(Long id) {
		Optional<Planet> planets = planetsDao.findById(id);
		
		if(planets == null) {
			throw new PlanetNaoEncontradoException("Planet Not Found");
		}
		return planets;
	}
	
	public PlanetsDAO getPlanetsDao() {
		return planetsDao;
	}

	public void setPlanetsDao(PlanetsDAO planetsDao) {
		this.planetsDao = planetsDao;
	}

	public Planet salvar(Planet planets) {
		planets.setId(null);
		return planetsDao.save(planets);
	}
	
	public void deletar(Long id) {
		try {
			planetsDao.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new PlanetNaoEncontradoException("Planet Not Found");
		}
	}
	
	public void atualizar(Planet planets) {
		verificarExistencia(planets);
		planetsDao.save(planets);
	}
	
	private void verificarExistencia(Planet planets) {
		buscar(planets.getId());
	}
	
	}
