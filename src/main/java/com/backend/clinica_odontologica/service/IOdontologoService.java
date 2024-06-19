package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.exceptios.ResourceNotFoundException;


import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto registrarOdontologo (OdontologoEntradaDto odontologoEntradaDto);
    List<OdontologoSalidaDto> listarOdontologos();
    OdontologoSalidaDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
    OdontologoSalidaDto actualizarOdontologo( OdontologoEntradaDto odontologoEntradaDtoq, Long id)throws ResourceNotFoundException;

}
