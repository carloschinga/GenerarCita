package com.gestorclinica.GenerarCita.controller;

import com.gestorclinica.GenerarCita.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final FileStorageService fileStorageService;

    @Autowired
    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
        this.fileStorageService.init(); // Inicializa la ruta de almacenamiento al iniciar la aplicación
    }

    /**
     * Endpoint para subir la imagen de una Especialidad.
     * @param file El archivo de la imagen.
     * @param specialtyName El nombre de la especialidad (para usar como subdirectorio).
     */
    @PostMapping("/especialidades/{specialtyName}")
    public ResponseEntity<?> uploadSpecialtyImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable String specialtyName) {

        try {
            // Guardar en una carpeta con el nombre de la especialidad (ej: /uploads/cardiologia/...)
            String filePath = fileStorageService.storeFile(file, "especialidades/" + specialtyName.toLowerCase());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Imagen de especialidad subida exitosamente.");
            response.put("filePath", filePath);

            // Aquí puedes llamar a tu servicio de base de datos para actualizar la ruta de la imagen
            // specialtyService.updateImagePath(specialtyName, filePath);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error al subir imagen: " + ex.getMessage());
        }
    }

    /**
     * Endpoint para subir la imagen de un Médico.
     * @param file El archivo de la imagen.
     * @param doctorId El ID o nombre del médico (para usar como subdirectorio).
     */
    @PostMapping("/doctor/{doctorId}")
    public ResponseEntity<?> uploadDoctorImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable String doctorId) {

        try {
            // Guardar en una carpeta con el ID del médico (ej: /uploads/doctors/roberto_m/...)
            String filePath = fileStorageService.storeFile(file, "doctors/" + doctorId.toLowerCase());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Imagen de médico subida exitosamente.");
            response.put("filePath", filePath);

            // doctorService.updateImagePath(doctorId, filePath);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error al subir imagen: " + ex.getMessage());
        }
    }
}