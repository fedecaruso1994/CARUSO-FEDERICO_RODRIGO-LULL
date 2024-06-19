package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.exceptios.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    IOdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaGuardarUnOdontologoCorrectamente() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(112233, "Pepe", "Argento");
        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologoEntradaDto);
        assertNotNull(odontologoRegistrado.getId());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnOdontologoCorrectamente() {
        assertDoesNotThrow(() -> odontologoService.buscarOdontologoPorId(1L));
    }

    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnOdontologoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologoPorId(9999L));
    }

    @Test
    @Order(4)
    void deberiaListarOdontologosExistentes() {
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

    @Test
    @Order(5)
    void deberiaEliminarUnOdontologo() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(6)
    void deberiaFallarAlEliminarUnOdontologoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(9999L));
    }

    @Test
    @Order(7)
    void deberiaListarUnaListaVaciaDeOdontologos() {
        assertTrue(odontologoService.listarOdontologos().isEmpty());
    }
}