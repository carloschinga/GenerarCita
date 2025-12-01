package com.gestorclinica.GenerarCita.service;

import com.gestorclinica.GenerarCita.model.Servicio;
import com.gestorclinica.GenerarCita.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    @Autowired
    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    /**
     * Obtiene la lista de servicios médicos distintos.
     * @return Lista de objetos Servicio.
     */
    public List<Servicio> obtenerServiciosDistintos() {
        // Llama al método que usa la consulta nativa DISTINCT del Repository
        return servicioRepository.findDistinctServiciosNative();

        // O si confías en que el ID es único y JpaRepository.findAll() es suficiente:
        // return servicioRepository.findAll();
    }
}