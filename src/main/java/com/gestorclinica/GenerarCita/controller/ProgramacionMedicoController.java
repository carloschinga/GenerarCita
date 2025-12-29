package com.gestorclinica.GenerarCita.controller;

import com.gestorclinica.GenerarCita.model.Medico;
import com.gestorclinica.GenerarCita.model.ProgramacionMedico;
import com.gestorclinica.GenerarCita.model.dto.ProgramacionMedicoCita;
import com.gestorclinica.GenerarCita.model.dto.ProgramacionMedicoRequest;
import com.gestorclinica.GenerarCita.model.dto.ProgramacionMedicosTurnoHabilitado;
import com.gestorclinica.GenerarCita.model.dto.TurnoProgramacionDias;
import com.gestorclinica.GenerarCita.service.MedicoServicio;
import com.gestorclinica.GenerarCita.service.ProgramacionMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/listarByServicio/{sercod}")
    public ResponseEntity<List<ProgramacionMedico>> getProgramacionMedicosByServicio(@PathVariable String sercod) {
        List<ProgramacionMedico> programacionMedicos = programacionMedicoService.obtenerProgramacionMedicoBySercod(sercod);

        if (programacionMedicos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(programacionMedicos); // 200 OK
    }

    @PostMapping("/listarDias")
    public ResponseEntity<List<TurnoProgramacionDias>> getDias(@RequestBody ProgramacionMedicoRequest t) {
        List<TurnoProgramacionDias> programacionMedicos = programacionMedicoService.obtenerProgramacionDias(t);

        if (programacionMedicos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(programacionMedicos); // 200 OK
    }

    @PostMapping("/listarTurnoPorDia")
    public ResponseEntity<List<ProgramacionMedicosTurnoHabilitado>> getTurnosPorDia(@RequestBody ProgramacionMedicoRequest t) {
        List<ProgramacionMedicosTurnoHabilitado> programacionMedicos = programacionMedicoService
                .obtenerProgramacionTurnosByDia(t);

        if (programacionMedicos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(programacionMedicos); // 200 OK
    }

    @PostMapping("/listarCitas")
    public ResponseEntity<List<ProgramacionMedicoCita>> getCitas(@RequestBody ProgramacionMedicoRequest t) {
        List<ProgramacionMedicoCita> programacionMedicos = programacionMedicoService.obtenerProgramacionCitas(t);

        if (programacionMedicos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(programacionMedicos); // 200 OK
    }
}