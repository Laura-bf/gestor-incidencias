package com.laura.gestorincidencias.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.laura.gestorincidencias.dto.IncidenciaDTO;
import com.laura.gestorincidencias.entity.Incidencia;

@Mapper(componentModel = "spring")
public interface IncidenciaMapper {
	
	IncidenciaDTO toDto(Incidencia entity);
	
	Incidencia toEntity(IncidenciaDTO dto);
	
	List<IncidenciaDTO> toDtoList(List<Incidencia> entities);
	
	List<Incidencia> toEntityList(List<IncidenciaDTO> dtos);
	

}
