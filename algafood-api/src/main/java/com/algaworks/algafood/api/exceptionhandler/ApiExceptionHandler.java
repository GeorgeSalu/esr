package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e) {
		Problema problema = new Problema();
		problema.setDataHora(LocalDateTime.now());
		problema.setMensagem(e.getMessage());
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e) {
		Problema problema = new Problema();
		problema.setDataHora(LocalDateTime.now());
		problema.setMensagem(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problema);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
		Problema problema = new Problema();
		problema.setDataHora(LocalDateTime.now());
		problema.setMensagem("O tipo de midia não é aceito");
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(problema);
	}
	
}
