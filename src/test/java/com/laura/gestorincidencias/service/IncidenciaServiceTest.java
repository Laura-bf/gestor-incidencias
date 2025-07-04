package com.laura.gestorincidencias.service;

import com.laura.gestorincidencias.entity.Incidencia;
import com.laura.gestorincidencias.repository.IncidenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/*Test unitario puro (sin cargar Spring)
 * Características
    No arranca contexto Spring
    Instancias clases y mocks manualmente con Mockito
    Rápido, sencillo y aislado
    Útil para lógica de negocio simple y rápida verificación*/
public class IncidenciaServiceTest {

    private IncidenciaRepository repository;
    private IncidenciaService service;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(IncidenciaRepository.class);
        service = new IncidenciaService(repository);
    }

    @Test
    public void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(new Incidencia()));
        List<Incidencia> result = service.findAll();
        assertThat(result).hasSize(1);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Incidencia incidencia = new Incidencia();
        incidencia.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(incidencia));
        Optional<Incidencia> result = service.findById(1L);
        assertThat(result).isPresent();
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Incidencia incidencia = new Incidencia();
        when(repository.save(incidencia)).thenReturn(incidencia);
        Incidencia result = service.save(incidencia);
        assertThat(result).isNotNull();
        verify(repository, times(1)).save(incidencia);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
