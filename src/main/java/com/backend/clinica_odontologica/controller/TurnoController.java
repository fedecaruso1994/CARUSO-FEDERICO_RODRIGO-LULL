//package com.backend.clinica_odontologica.controller;
//
//import com.backend.clinica_odontologica.entity.Odontologo;
//import com.backend.clinica_odontologica.service.IOdontologoService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//
//public class TurnoController {
//
//    private ITurnoService turnoService;
//
//    public turnoController(IturnoService turnoService) {
//        this.turnoService = turnoService;
//    }
//
//    //POST
//    @PostMapping("/registrar")
//    public Turno registrarTurno(@RequestBody Turno turno){
//        return turnoService.registrarTurno(turno);
//    }
//
//    //GET
//    @GetMapping("/listar")
//    public List<turno> listarTurno(){
//        return turnoService.listarTurno();
//    }
//
//}
