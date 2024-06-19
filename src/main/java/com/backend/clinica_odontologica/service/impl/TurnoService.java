package com.backend.clinica_odontologica.service.impl;
import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.entity.Turno;
import com.backend.clinica_odontologica.exceptios.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(ModelMapper modelMapper, TurnoRepository turnoRepository) {
        this.modelMapper = modelMapper;
        this.turnoRepository = turnoRepository;

        configureMapping();
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {

        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turnoEntradaDto));
        Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
        LOGGER.info("TurnoEntidad: " + JsonPrinter.toString(turno));

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDto.class);
        LOGGER.info("TurnoSalidaDto: " + JsonPrinter.toString(turnoSalidaDto));

        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
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
        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto;

        if(turnoAActualizar != null){

            turnoRecibido.setId(turnoAActualizar.getId());
            turnoRecibido.getOdontologo().setId(turnoAActualizar.getOdontologo().getId());;
            turnoRecibido.getPaciente().setId(turnoAActualizar.getPaciente().getId());
            turnoAActualizar = turnoRecibido;


            turnoRepository.save(turnoAActualizar);
            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No existe el registro con turno id: " + id);
        }
        return turnoSalidaDto;
    }



    private void configureMapping(){
        // Configuración de mapeo de TurnoEntradaDto a Turno
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class).addMappings(mapper -> {
            mapper.map(TurnoEntradaDto::getPacienteEntradaDto, Turno::setPaciente);
            mapper.map(TurnoEntradaDto::getOdontologoEntradaDto, Turno::setOdontologo);
        });

        // Configuración de mapeo de Turno a TurnoSalidaDto
        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class).addMappings(mapper -> {
            mapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteSalidaDto);
            mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoSalidaDto);
        });
    }

}
