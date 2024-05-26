package com.backend.clinica_odontologica.repository;

import java.util.List;

public interface IDao <T> {
    T registrar(T t);
    List<T> listarTodos();
    T buscarPorId(Long id);
}