package com.laura.gestorincidencias.service;

import com.laura.gestorincidencias.entity.Incidencia;
import com.laura.gestorincidencias.repository.IncidenciaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository repository;

    public List<Incidencia> findAll() {
        return repository.findAll();
    }

    public Optional<Incidencia> findById(Long id) {
        return repository.findById(id);
    }

    public Incidencia save(Incidencia incidencia) {
        return repository.save(incidencia);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
