package com.laura.gestorincidencias.controller;

import com.laura.gestorincidencias.dto.IncidenciaDTO;
import com.laura.gestorincidencias.entity.Incidencia;
import com.laura.gestorincidencias.exception.IncidenciaNotFoundException;
import com.laura.gestorincidencias.mapper.IncidenciaMapper;
import com.laura.gestorincidencias.service.IncidenciaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
public class IncidenciaController {

	private final IncidenciaMapper mapper;
    private final IncidenciaService service;

    @GetMapping
    public ResponseEntity<List<IncidenciaDTO>> getAll() {
    	
        return ResponseEntity.ok(mapper.toDtoList(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> getById(@PathVariable Long id) {
        return service.findById(id)
        		.map(mapper::toDto)
                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
                .orElseThrow(() -> new IncidenciaNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<IncidenciaDTO> create(@Valid @RequestBody IncidenciaDTO incidenciaDTO) {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo(incidenciaDTO.getTitulo());
        incidencia.setDescripcion(incidenciaDTO.getDescripcion());
        
    	Incidencia saved = service.save(incidencia);
    	
    	IncidenciaDTO response = mapper.toDto(saved);
    	
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> update(@PathVariable Long id, @Valid @RequestBody IncidenciaDTO incidenciaDTO) {
    	 
    	Incidencia incidencia = mapper.toEntity(incidenciaDTO);
    	  
    	return service.findById(id)
                .map(existing -> {
                    incidencia.setId(existing.getId());
                    Incidencia updated = service.save(incidencia);
                    return ResponseEntity.ok(mapper.toDto(updated));
                })
                .orElseThrow(() -> new IncidenciaNotFoundException(id));
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Incidencia> opt = service.findById(id);
        if (opt.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new IncidenciaNotFoundException(id);
        }
    }


}
