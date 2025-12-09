package com.gestorclinica.GenerarCita.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestorclinica.GenerarCita.model.MedicosFotos;
import com.gestorclinica.GenerarCita.service.FileStorageService;

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
	 * 
	 * @param file          El archivo de la imagen.
	 * @param specialtyName El nombre de la especialidad (para usar como
	 *                      subdirectorio).
	 */
	@PostMapping("/especialidades/{specialtyName}")
	public ResponseEntity<?> uploadSpecialtyImage(@RequestParam("file") MultipartFile file,
			@PathVariable String specialtyName) {

		try {
			// Guardar en una carpeta con el nombre de la especialidad (ej:
			// /uploads/cardiologia/...)
			String filePath = fileStorageService.storeFile(file, "especialidades/" + specialtyName.toLowerCase());

			Map<String, String> response = new HashMap<>();
			response.put("message", "Imagen de especialidad subida exitosamente.");
			response.put("filePath", filePath);

			// Aquí puedes llamar a tu servicio de base de datos para actualizar la ruta de
			// la imagen
			// specialtyService.updateImagePath(specialtyName, filePath);

			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Error al subir imagen: " + ex.getMessage());
		}
	}

	/**
	 * Endpoint para subir la imagen de un Médico.
	 * 
	 * @param file     El archivo de la imagen.
	 * @param doctorId El ID o nombre del médico (para usar como subdirectorio).
	 */
	@PostMapping(value = "/doctor/upload/{doctorId}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadDoctorImage(@RequestParam("file") MultipartFile file,
			@PathVariable String doctorId) {

		try {
			
			if (file.getSize() > 10 * 1024 * 1024) {
	            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
	                .body(Map.of("error", "El archivo es demasiado grande. Máximo 10MB"));
	        }
			// Guardar en una carpeta con el ID del médico (ej:
			// /uploads/doctors/roberto_m/...)
			String filePath = fileStorageService.storeFile(file, "doctors/" + doctorId.toLowerCase());

			fileStorageService.saveOrUpdateFile(doctorId, filePath);

			Map<String, String> response = new HashMap<>();
			response.put("message", "Imagen de médico subida exitosamente.");
			response.put("filePath", filePath);

			// doctorService.updateImagePath(doctorId, filePath);

			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Error al subir imagen: " + ex.getMessage());
		}
	}

	@GetMapping("/doctor/getFoto/{doctorId}")
	public ResponseEntity<?> getFoto(@PathVariable String doctorId) {

		List<MedicosFotos> fotos = fileStorageService.getUrlFoto(doctorId);

		if (fotos.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204 No Content si la lista está vacía
		}

		List<Map<String, String>> response = fotos.stream().map(foto -> {
			Map<String, String> map = new HashMap<>();
			map.put("url", "http://181.224.248.20/appfotos/" + foto.getUrlFoto());
			map.put("medcod", foto.getMedcod());
			return map;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(response); // 200 OK
	}

}