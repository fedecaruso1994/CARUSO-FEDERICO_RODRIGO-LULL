package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.repository.IDao;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    private IDao<Odontologo> odontologoIDao;
    private final ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {

        //Logica de negocio
        //Mapeo de DTO a entidad
        LOGGER.info("OdontologoEntradaDto: "+ odontologoEntradaDto);
        Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        LOGGER.info("Odontologo Entidad: "+ odontologo);
        odontologoIDao.registrar(odontologo);
        Odontologo odontologoRegistrado = odontologoIDao.registrar(odontologo);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoRegistrado, OdontologoSalidaDto.class);
        //mapeor de entidad a dto
        LOGGER.info("Odontologo Salida: "+ odontologoSalidaDto);
        return odontologoSalidaDto;
    }


    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        //odontologoIDao.listarTodos()
        //mapeo de lista de entidades a lista de dtos
        return null;
    }

}