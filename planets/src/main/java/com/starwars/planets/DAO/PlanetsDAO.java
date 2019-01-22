package com.starwars.planets.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwars.planets.domain.Planet;

public interface PlanetsDAO extends JpaRepository<Planet, Long>{

	

}
