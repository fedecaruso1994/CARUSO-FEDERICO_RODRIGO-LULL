package com.backend.clinica_odontologica;

import com.backend.clinica_odontologica.repository.dbconnection.H2Connection;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class ClinicaOdontologicaApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);
	public static void main(String[] args) {

		H2Connection.ejecutarScriptInicial();

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		LOGGER.info("Proyecto corriendo...");
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
