package com.laura.gestorincidencias.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.laura.gestorincidencias.entity.Incidencia;
import com.laura.gestorincidencias.repository.IncidenciaRepository;

/*Test con contexto Spring (@SpringBootTest, @WebMvcTest)
Características
    • Arranca parte o todo el contexto Spring
    • Usa anotaciones como @MockBean para inyectar mocks
    • Más lento que test puro
    • Útil para tests de integración parcial o completa (controladores, servicios, repositorios)*/

@SpringBootTest
public class IncidenciaServiceSpringTest {
	
	//Usamos @MockBean para mockear el repositorio dentro del contexto Spring.
	@MockBean
	private IncidenciaRepository repository;
	
	//@Autowired inyecta el servicio real.
	@Autowired
	private IncidenciaService service;
		
	@Test
	public void whenFindAll_thenReturnList() {
		Incidencia incidencia = new Incidencia();
		incidencia.setId(1L);
		incidencia.setTitulo("título");
		incidencia.setDescripcion("descripción");
		
		Incidencia incidencia2 = new Incidencia();
		incidencia2.setId(2L);
		incidencia2.setTitulo("título");
		incidencia2.setDescripcion("descripción");
		
		List<Incidencia> incidenciaList = List.of(incidencia, incidencia2);
		
		Mockito.when(repository.findAll()).thenReturn(incidenciaList);
		
		List<Incidencia> result = service.findAll();
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(2, result.size());
	}
	
	@Test
	public void whenFindById_thenReturnIncidencia() {
		Incidencia incidencia = new Incidencia();
		incidencia.setId(1L);
		incidencia.setTitulo("título");
		incidencia.setDescripcion("descripción");
		
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(incidencia));
		
		Optional<Incidencia> result = service.findById(1L);
		
		Assertions.assertTrue(result.isPresent());
		Assertions.assertEquals("título", result.get().getTitulo());
	}
	
	@Test
	public void whenSave_thenReturnIncidencia() {
		Incidencia incidencia = new Incidencia();
		incidencia.setId(1L);
		incidencia.setTitulo("título");
		incidencia.setDescripcion("descripción");
		
		Mockito.when(repository.save(incidencia)).thenReturn(incidencia);
		
		Incidencia result = service.save(incidencia);
		
		Assertions.assertEquals(incidencia.toString(), result.toString());
		Assertions.assertNotNull(incidencia);
	}
	
	@Test
	public void whenDelete_thenReturnNothing() {
		Long idToDelete = 1L;
		
		// No hace falta definir comportamiento en repository.deleteById porque es void
		
		service.deleteById(idToDelete);
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(idToDelete);
	}

}
