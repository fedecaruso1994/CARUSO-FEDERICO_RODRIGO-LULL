package com.backend.clinica_odontologica.repository.impl;

import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.repository.IDao;
import com.backend.clinica_odontologica.repository.dbconnection.H2Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);
    Connection connection = null;

    @Override
    public Odontologo registrar(Odontologo odontologo) {

        Odontologo odontologoRegistrado = null;
        String insert = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);


            PreparedStatement psInsert = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();

            connection.commit();

            ResultSet resultSet = psInsert.getGeneratedKeys();

            while (resultSet.next()) {
                odontologoRegistrado = new Odontologo(resultSet.getLong("id"), odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }

            LOGGER.info("Odontologo registrdo: " + odontologoRegistrado);


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.error("Tuvimos un problema. " + e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (Exception ex) {
                        LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
                    }
                }
            }


        }
        return odontologoRegistrado;
    }

    @Override
    public List<Odontologo> listarTodos () {
        LOGGER.info("Estamos listando odontologos");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();

            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");


            ResultSet result = psSelect.executeQuery();

            while (result.next()) {

                Long id = result.getLong("id");
                int matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");

                Odontologo odontologo = new Odontologo (id, matricula, nombre , apellido);



                odontologos.add(odontologo);
            }
            psSelect.close();

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("Esta es la lista que se está devolviendo " + odontologos);
        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Odontologo odontologoBuscado = null;
        Connection connection = null;

        try{
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                odontologoBuscado = new Odontologo(resultSet.getLong(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        if(odontologoBuscado == null) LOGGER.error("No se ha encontrado el odontologo con id: " + id);
        else LOGGER.info("Se ha encontrado el odontologo: " + odontologoBuscado);

        return odontologoBuscado;
    }
}
