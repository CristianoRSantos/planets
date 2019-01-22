package com.starwars.planets.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.starwars.planets.domain.DetalhesErro;
import com.starwars.planets.services.exceptions.PlanetNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
			
		@ExceptionHandler(PlanetNaoEncontradoException.class)
		public ResponseEntity<DetalhesErro> handlePlanetNotFoundException
										(PlanetNaoEncontradoException e, HttpServletRequest request) {
			DetalhesErro erro = new DetalhesErro();
			erro.setStatus(404l);
			erro.setTitulo("Planet Não Encontrado");
			erro.setMensagemDesenvolvedor("http://erros.planets.com/404/");
			erro.setTimeStamp(System.currentTimeMillis());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		}
	}
