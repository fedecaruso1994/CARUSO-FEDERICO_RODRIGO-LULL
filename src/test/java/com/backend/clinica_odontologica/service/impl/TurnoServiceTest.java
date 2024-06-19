package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.exceptios.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IOdontologoService;
import com.backend.clinica_odontologica.service.IPacienteService;
import com.backend.clinica_odontologica.service.ITurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TurnosServiceTest {

    @Autowired
    ITurnoService turnoService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    IPacienteService pacienteService;


    DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Rivadavia", 7895, "Solymar", "Canelones");
    OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(1111, "Luis", "Suarez");
    PacienteEntradaDto pacienteEntradaDto =  new PacienteEntradaDto("Leo", "Messi", 456789, LocalDate.now(), domicilioEntradaDto);

    @Test
    @Order(1)
    void deberiaGuardarUnTurnoCorrectamente() {
        TurnoEntradaDto turnoDtoEntrada = new TurnoEntradaDto(LocalDateTime.now(), pacienteEntradaDto, odontologoEntradaDto);
        assertDoesNotThrow(() -> {
            TurnoSalidaDto turnoGuardado = turnoService.registrarTurno(turnoDtoEntrada);
            assertNotNull(turnoGuardado.getId());
        });
    }

    @Test
    @Order(2)
    void deberiaBuscarUnTurnoCorrectamente() {
        assertDoesNotThrow(() -> turnoService.buscarTurnoPorId(1L));
    }

    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnTurnoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurnoPorId(9999L));
    }

    @Test
    @Order(4)
    void deberiaListarTurnosExistentes() {
        assertFalse(turnoService.listarTurnos().isEmpty());
    }

    @Test
    @Order(5)
    void deberiaEliminarUnTurno() {
        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(6)
    void deberiaFallarAlEliminarUnTurnoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(9999L));
    }

    @Test
    @Order(7)
    void deberiaListarUnaListaVaciaDeTurnos() {
        assertTrue(turnoService.listarTurnos().isEmpty());
    }
}