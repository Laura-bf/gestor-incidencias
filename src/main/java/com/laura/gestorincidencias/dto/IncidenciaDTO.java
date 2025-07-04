package com.laura.gestorincidencias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IncidenciaDTO {

	private Long id;
	
	@NotBlank(message = "El título es obligatorio")
	@Size(max = 100, message = "El título no puede tener más de 100 caracteres")  
	private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String descripcion;

}
