package com.backend.clinica_odontologica.service;


import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;

import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;


import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto);
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurnoPorId(Long id);
    void eliminarTurno(Long id);
    TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id);

}
