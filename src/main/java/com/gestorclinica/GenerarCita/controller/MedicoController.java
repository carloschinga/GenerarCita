package com.gestorclinica.GenerarCita.controller;


import com.gestorclinica.GenerarCita.model.Medico;
import com.gestorclinica.GenerarCita.model.Servicio;
import com.gestorclinica.GenerarCita.service.MedicoServicio;
import com.gestorclinica.GenerarCita.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoServicio medicoService;

    @Autowired
    public MedicoController(MedicoServicio medicoService) {
        this.medicoService = medicoService;
    }

    /**
     * Endpoint para obtener la lista de servicios médicos (códigos y descripciones)
     * Respuesta: 200 OK con el cuerpo JSON de la lista.
     * URL: GET /api/servicios/distintos
     */
    @GetMapping("/distintos")
    public ResponseEntity<List<Medico>> getServiciosDistintos() {
        List<Medico> medicos = medicoService.obtenerMedicoDistintos();

        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(medicos); // 200 OK
    }
}