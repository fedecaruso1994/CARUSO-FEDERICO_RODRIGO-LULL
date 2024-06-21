package com.backend.clinica_odontologica.dto.entrada;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @Positive(message = "La matricula no puede ser nulo o menor a cero")
    @Digits(integer = 9, fraction = 0, message = "La matricula puede tener como maximo nueve digitos")
    private int matricula;


    @NotBlank(message = "El campo nombre no puede estar vacio")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacio")
    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
