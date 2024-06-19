package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.exceptios.ResourceNotFoundException;


import java.util.List;

public interface IPacienteService {

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(Long id) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) throws ResourceNotFoundException;

}


