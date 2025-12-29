package com.gestorclinica.GenerarCita.service;

import com.gestorclinica.GenerarCita.model.Medico;
import com.gestorclinica.GenerarCita.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServicio {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoServicio(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    /**
     * Obtiene la lista de servicios médicos distintos.
     * @return Lista de objetos Servicio.
     */
    public List<Medico> obtenerMedicoDistintos() {
        // Llama al método que usa la consulta nativa DISTINCT del Repository
        return medicoRepository.findDistinctMedicosNative();

        // O si confías en que el ID es único y JpaRepository.findAll() es suficiente:
        // return servicioRepository.findAll();
    }


    /**
     * Obtiene la lista de servicios médicos distintos.
     * @return Lista de objetos Servicio.
     */
    public List<Medico> obtenerMedicoDistintos(String sercod) {
        // Llama al método que usa la consulta nativa DISTINCT del Repository
        return medicoRepository.findDistinctMedicosBySercod(sercod);

        // O si confías en que el ID es único y JpaRepository.findAll() es suficiente:
        // return servicioRepository.findAll();
    }

    public List<Medico> obtenerMedicoBySercod(String sercod) {
        return medicoRepository.findDistinctMedicosBySercod(sercod);
    }
}
