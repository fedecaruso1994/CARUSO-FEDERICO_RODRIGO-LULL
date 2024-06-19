package com.backend.clinica_odontologica.entity;

import javax.persistence.*;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50 , nullable = false)
    private int matricula;

    @Column(length = 50 , nullable = false)
    private String nombre;

    @Column(length = 50 , nullable = false)
    private String apellido;

    public Odontologo() {
    }

    public Odontologo(Long id, int matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }



    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getMatricula() { return matricula; }

    public void setMatricula(int matricula) { this.matricula = matricula; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }


}
