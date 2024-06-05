package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;

import com.backend.clinica_odontologica.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    //peticion http json <-> @RequestBody & @ResponseBody -> java dto -> controller dto -> servicio dto <-> entidad <-> persistencia entidad <-> base de datos
    //se trabaja con DTP

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/registrar")
    public PacienteSalidaDto registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
        return pacienteService.registrarPaciente(pacienteEntradaDto);
    }

    //GET
    @GetMapping("/listar")
    public List<PacienteSalidaDto> listarPacientes(){
        return pacienteService.listarPacientes();
    }



}
