package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
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
class TurnoServiceTest {

    @Autowired
    ITurnoService turnoService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    IPacienteService pacienteService;

    private Long odontologoId;
    private Long pacienteId;

    @BeforeAll
    void config () {
        if (odontologoId == null) {
            OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto (11111, "Leo", "Messi");
            OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologoEntradaDto);
            assertNotNull(odontologoRegistrado.getId());
            odontologoId = odontologoRegistrado.getId();
        }

        if (pacienteId == null) {
            DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Flores", 1245, "Goes", "Montevideo");
            PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Marcelo", "Tinelli", 5678956
                    , LocalDate.now(), domicilioEntradaDto);
            PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(pacienteEntradaDto);
            assertNotNull(pacienteRegistrado.getId());
            pacienteId = pacienteRegistrado.getId();
        }
    }
    @Test
    @Order(1)
    void deberiaRegistrarUnTurno() {
        TurnoEntradaDto turnoDtoEntrada = new TurnoEntradaDto(LocalDateTime.now(), odontologoId, pacienteId);
        assertDoesNotThrow(() -> {
            TurnoSalidaDto turnoRegistrado = turnoService.registrarTurno(turnoDtoEntrada);
            assertNotNull(turnoRegistrado.getId());
        });
    }
    @Test
    @Order(2)
    void deberiaBuscarUnTurnoPorSuId() {
        assertDoesNotThrow(() -> turnoService.buscarTurnoPorId(1L));
    }
    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnTurnoQueNoExiste() {
        assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurnoPorId(9999L));
    }

    @Test
    @Order(4)
    void deberiaListarTurnosExistentes() {
        assertFalse(turnoService.listarTurnos().isEmpty());
    }


}