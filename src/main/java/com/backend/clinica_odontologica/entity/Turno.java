package com.backend.clinica_odontologica.entity;

import java.time.LocalDateTime;

public class Turno {

    private Long id;
    private LocalDateTime fechaYHora;
    private Paciente paciente;
    private Odontologo odontologo;


    public Turno(LocalDateTime fechaYHora, Paciente paciente, Odontologo odontologo) {
        this.fechaYHora = fechaYHora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Turno(Long id, LocalDateTime fechaYHora, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() { return fechaYHora; }

    public void setFechaYHora(LocalDateTime fechaYHora) { this.fechaYHora = fechaYHora; }

    public Paciente getPaciente() { return paciente; }

    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Odontologo getOdontologo() { return odontologo; }

    public void setOdontologo(Odontologo odontologo) { this.odontologo = odontologo; }

    @Override
    public String toString() {
        return "Id: " + id + " - Fecha y hora " + fechaYHora + " - Paciente: " + paciente + " Odontólogo" + odontologo ;
    }
}
