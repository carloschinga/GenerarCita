package com.gestorclinica.GenerarCita.service;

import com.gestorclinica.GenerarCita.model.Medico;
import com.gestorclinica.GenerarCita.model.ProgramacionMedico;
import com.gestorclinica.GenerarCita.repository.MedicoRepository;
import com.gestorclinica.GenerarCita.repository.ProgramacionMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
