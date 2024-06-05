package com.backend.clinica_odontologica.controller;


import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //POST
    @PostMapping("/registrar")
    public OdontologoSalidaDto registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto){
        return odontologoService.registrarOdontologo(odontologoEntradaDto);
    }

    //GET
    @GetMapping("/listar")
    public List<OdontologoSalidaDto> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }


}
