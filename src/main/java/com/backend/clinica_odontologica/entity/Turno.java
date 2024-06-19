package com.backend.clinica_odontologica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime fechaYHora;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;



    public Turno(Long id, LocalDateTime fechaYHora, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Turno() {
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


}
