package com.starwars.planets.services.exceptions;

public class PlanetNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1501989666320022765L;

	public PlanetNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PlanetNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
