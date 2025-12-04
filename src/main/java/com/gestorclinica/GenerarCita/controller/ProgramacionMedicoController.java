package com.gestorclinica.GenerarCita.controller;

import com.gestorclinica.GenerarCita.model.Medico;
import com.gestorclinica.GenerarCita.model.ProgramacionMedico;
import com.gestorclinica.GenerarCita.service.MedicoServicio;
import com.gestorclinica.GenerarCita.service.ProgramacionMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/programacionmedicos")
public class ProgramacionMedicoController {

    private final ProgramacionMedicoService programacionMedicoService;

    @Autowired
    public ProgramacionMedicoController(ProgramacionMedicoService programacionMedicoService) {
        this.programacionMedicoService = programacionMedicoService;
    }

    /**
     * Endpoint para obtener la lista de servicios médicos (códigos y descripciones)
     * Respuesta: 200 OK con el cuerpo JSON de la lista.
     * URL: GET /api/servicios/distintos
     */
    @GetMapping("/listar")
    public ResponseEntity<List<ProgramacionMedico>> getProgramacionMedicosDistintos() {
        List<ProgramacionMedico> programacionMedicos = programacionMedicoService.obtenerProgramacionMedicoDistintos();

        if (programacionMedicos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(programacionMedicos); // 200 OK
    }
}