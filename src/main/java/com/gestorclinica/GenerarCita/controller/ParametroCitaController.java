package com.gestorclinica.GenerarCita.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorclinica.GenerarCita.model.ParametroCita;
import com.gestorclinica.GenerarCita.model.ParametroCitaRequest;
import com.gestorclinica.GenerarCita.service.ParametroCitaService;

@RestController
@RequestMapping("/parametro")
public class ParametroCitaController {

    @Autowired
    private ParametroCitaService parametroCitaService;

    @PostMapping("/update")
    public ResponseEntity<?> updateParametro(@RequestBody ParametroCitaRequest t) {
        try {

            parametroCitaService.updateParametroCita(t.getCodiParam(), t.getNombParam(), t.getValuParam());

            Map<String, String> response = new HashMap<>();
            response.put("resultado", "ok");
            response.put("message", "Parametro actualizado exitosamente.");

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error al subir imagen: " + ex.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ParametroCita>> getParametro() {
        List<ParametroCita> parametro = parametroCitaService.obtenerParametroCita();

        if (parametro.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
        }

        return ResponseEntity.ok(parametro); // 200 OK
    }

}
