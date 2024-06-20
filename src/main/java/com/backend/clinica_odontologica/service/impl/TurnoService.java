package com.backend.clinica_odontologica.service.impl;
import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.DomicilioSalidaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.entity.Turno;
import com.backend.clinica_odontologica.exceptios.BadRequestException;
import com.backend.clinica_odontologica.exceptios.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;


    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {

        Turno turnoARegistrar = new Turno();
        turnoARegistrar.setFechaYHora(turnoEntradaDto.getFechaYHora());

        OdontologoSalidaDto odontologoSalidaDto = null;
        try {
            odontologoSalidaDto = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());
        } catch (ResourceNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }

        PacienteSalidaDto pacienteSalidaDto = null;
        try {
            pacienteSalidaDto = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        } catch (ResourceNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }

        Odontologo odontologo = modelMapper.map(odontologoSalidaDto, Odontologo.class);
        Paciente paciente = modelMapper.map(pacienteSalidaDto, Paciente.class);

        odontologo = entityManager.merge(odontologo);
        paciente = entityManager.merge(paciente);

        turnoARegistrar.setOdontologo(odontologo);
        turnoARegistrar.setPaciente(paciente);

        Turno turnoGuardado = turnoRepository.save(turnoARegistrar);
        LOGGER.info("Turno guardado con éxito: " + JsonPrinter.toString(turnoGuardado));
        return convertirA(turnoGuardado);
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> convertirA(turno))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No existe el registro con turno id: " + id);
        }

        return turnoEncontrado;

    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if(buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            LOGGER.warn("No se encontró el turno con ID: " + id);
            throw new ResourceNotFoundException("No existe el registro con turno id: " + id);
        }

    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws ResourceNotFoundException {
        Turno turnoExistente = turnoRepository.findById(id).orElse(null);
        if (turnoExistente == null) {
            throw new ResourceNotFoundException("No existe el registro con turno id: " + id);
        }

        turnoExistente.setFechaYHora(turnoEntradaDto.getFechaYHora());

        OdontologoSalidaDto odontologoSalidaDto = null;

        try {
            odontologoSalidaDto = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());
        } catch (ResourceNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }

        turnoExistente.setOdontologo(modelMapper.map(odontologoSalidaDto, Odontologo.class));

        PacienteSalidaDto pacienteSalidaDto = null;

        try {
            pacienteSalidaDto = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        } catch (ResourceNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }

        turnoExistente.setPaciente(modelMapper.map(pacienteSalidaDto, Paciente.class));

        turnoExistente = turnoRepository.save(turnoExistente);
        LOGGER.info("Turno actualizado con éxito: " + JsonPrinter.toString(turnoExistente));
        return convertirA(turnoExistente);
    }



    private TurnoSalidaDto convertirA (Turno turno) {
        TurnoSalidaDto turnoDtoSalida = modelMapper.map(turno, TurnoSalidaDto.class);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(turno.getOdontologo(), OdontologoSalidaDto.class);
        DomicilioSalidaDto domicilioSalidaDto = modelMapper.map(turno.getPaciente().getDomicilio(), DomicilioSalidaDto.class);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(turno.getPaciente(), PacienteSalidaDto.class);
        pacienteSalidaDto.setDomicilioSalidaDto(domicilioSalidaDto);

        turnoDtoSalida.setOdontologoSalidaDto(odontologoSalidaDto);
        turnoDtoSalida.setPacienteSalidaDto(pacienteSalidaDto);

        return turnoDtoSalida;
    }

}
