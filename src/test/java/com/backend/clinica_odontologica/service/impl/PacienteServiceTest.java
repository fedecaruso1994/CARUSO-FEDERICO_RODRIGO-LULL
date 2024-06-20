package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.exceptios.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IPacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarElPacienteLuisSuarez() {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Bv Artigas", 1234, "Tres Cruces", "Montevideo");
        PacienteEntradaDto pacienteDtoEntrada = new PacienteEntradaDto("Luis", "Suarez", 456789, LocalDate.now(), domicilioEntradaDto);
        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(pacienteDtoEntrada);
        assertNotNull(pacienteRegistrado.getId());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnPacientePorSuId() {
        assertDoesNotThrow(() -> pacienteService.buscarPacientePorId(1L));
    }

    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnPacienteInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPacientePorId(555555L));
    }

    @Test
    @Order(4)
    void deberiaListarPacientesExistentes() {
        assertFalse(pacienteService.listarPacientes().isEmpty());
    }

    @Test
    @Order(5)
    void deberiaEliminarUnPaciente() {
        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(6)
    void deberiaFallarAlEliminarUnPacienteInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(55555L));
    }

}