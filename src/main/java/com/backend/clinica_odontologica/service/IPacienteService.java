package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente registrarPaciente(Paciente paciente);
    List<Paciente> listarPacientes();
}