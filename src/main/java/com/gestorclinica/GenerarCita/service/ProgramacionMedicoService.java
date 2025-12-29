package com.gestorclinica.GenerarCita.service;

import com.gestorclinica.GenerarCita.model.Medico;
import com.gestorclinica.GenerarCita.model.ProgramacionMedico;
import com.gestorclinica.GenerarCita.model.dto.*;
import com.gestorclinica.GenerarCita.repository.MedicoRepository;
import com.gestorclinica.GenerarCita.repository.ProgramacionMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProgramacionMedicoService {


    private final ProgramacionMedicoRepository programacionMedicoRepository;

    @Autowired
    public ProgramacionMedicoService(ProgramacionMedicoRepository programacionMedicoRepository) {
        this.programacionMedicoRepository= programacionMedicoRepository;
    }

    /**
     * Obtiene la lista de servicios médicos distintos.
     * @return Lista de objetos Servicio.
     */
    public List<ProgramacionMedico> obtenerProgramacionMedicoDistintos() {
        // Llama al método que usa la consulta nativa DISTINCT del Repository
        return programacionMedicoRepository.findDistinctProgramacionMedicosNative();

        // O si confías en que el ID es único y JpaRepository.findAll() es suficiente:
        // return servicioRepository.findAll();
    }

    public List<ProgramacionMedico> obtenerProgramacionMedicoBySercod(String sercod) {
        // Llama al método que usa la consulta nativa DISTINCT del Repository
        return programacionMedicoRepository.findDistinctProgramacionMedicosBySercod(sercod);
    }

    public List<TurnoProgramacionDias> obtenerProgramacionDias(ProgramacionMedicoRequest t) {
        return programacionMedicoRepository.findDiasProgramacion(t.getSercod(), t.getMedcod());
    }

    public List<ProgramacionMedicosTurnoHabilitado> obtenerProgramacionTurnosByDia(ProgramacionMedicoRequest t) {

        List<ProgramacionMedicosTurnoHabilitado> collection = new ArrayList<>();
        List<ProgramacionMedicoCita> citas = programacionMedicoRepository.findCitas(t.getSercod(), t.getMedcod(), t.getFecha());
        List<ProgramacionMedicoTurnos> turnos = programacionMedicoRepository.findTurnosProgramacion(t.getSercod(), t.getMedcod(), t.getFecha());

        Set<String> horasOcupadas = citas.stream()
                .map(ProgramacionMedicoCita::getHora_cita)
                .collect(Collectors.toSet());

        for (ProgramacionMedicoTurnos tt : turnos) {

            ProgramacionMedicosTurnoHabilitado turno = new ProgramacionMedicosTurnoHabilitado();

            turno.setServicio(tt.getServicio());
            turno.setFecha(tt.getFecha());
            turno.setMedico(tt.getMedico());
            turno.setHoraCita(tt.getHoraCita());
            turno.setSercod(t.getSercod());
            turno.setMedcod(t.getMedcod());

            boolean habilitado = !horasOcupadas.contains(tt.getHoraCita());
            turno.setEstado(habilitado);

            collection.add(turno);
        }


        return collection;
    }

    public List<ProgramacionMedicoCita> obtenerProgramacionCitas(ProgramacionMedicoRequest t) {
        return programacionMedicoRepository.findCitas(t.getSercod(), t.getMedcod(), t.getFecha());
    }

}
