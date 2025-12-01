package com.gestorclinica.GenerarCita.controller;

import com.gestorclinica.GenerarCita.model.Servicio;
import com.gestorclinica.GenerarCita.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    @Autowired
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    /**
     * Endpoint para obtener la lista de servicios médicos (códigos y descripciones)
     * Respuesta: 200 OK con el cuerpo JSON de la lista.
     * URL: GET /api/servicios/distintos
     */
    @GetMapping("/distintos")
    public ResponseEntity<List<Servicio>> getServiciosDistintos() {
        List<Servicio> servicios = servicioService.obtenerServiciosDistintos();

        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(servicios); // 200 OK
    }
}