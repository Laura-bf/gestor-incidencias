package com.laura.gestorincidencias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncidenciaNotFoundException extends RuntimeException{

	  private static final long serialVersionUID = 6235197777213516822L;

	public IncidenciaNotFoundException(Long id) {
	        super("Incidencia no encontrada con ID: " + id);
	    }
}
