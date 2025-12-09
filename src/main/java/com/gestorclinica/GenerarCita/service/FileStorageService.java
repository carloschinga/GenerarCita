package com.gestorclinica.GenerarCita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestorclinica.GenerarCita.model.MedicosFotos;
import com.gestorclinica.GenerarCita.repository.MedicoRepository;
import com.gestorclinica.GenerarCita.repository.MedicosFotosRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private MedicosFotosRepository MedicosFotosRepository;

	// Ruta donde se guardarán los archivos. Se define en application.properties
	@Value("${file.upload-dir}")
	private String fileUploadDir;

	private Path fileStorageLocation;

	// Inicializa la ubicación y crea el directorio si no existe
	public void init() {
		this.fileStorageLocation = Paths.get(fileUploadDir).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new RuntimeException("No se pudo crear el directorio de almacenamiento.", ex);
		}
	}

	/**
	 * Guarda el archivo subido en el sistema de archivos.
	 * 
	 * @param file El archivo MultipartFile recibido del controlador.
	 * @return El nombre único del archivo guardado.
	 */
	public String storeFile(MultipartFile file, String subDirectory) {
		// Normaliza el nombre del archivo
		String fileName = generateUniqueFileName(file.getOriginalFilename(), subDirectory);

		try {
			// Concatena la subcarpeta (especialidad/medico) a la ruta base
			Path targetDirectory = this.fileStorageLocation.resolve(subDirectory).normalize();
			Files.createDirectories(targetDirectory); // Asegura que la subcarpeta exista

			Path targetLocation = targetDirectory.resolve(fileName);

			// Copia el archivo al destino
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			// Devuelve la ruta relativa para ser guardada en la base de datos (ej:
			// cardiologia/ana_castillo.jpg)
			return subDirectory + "/" + fileName;

		} catch (IOException ex) {
			throw new RuntimeException("No se pudo guardar el archivo " + fileName + ". Por favor, inténtelo de nuevo!",
					ex);
		}
	}

	public void saveOrUpdateFile(String medcod, String url) {
		medicoRepository.saveOrUpdateMedicoFoto(medcod, url);
	}

	public List<MedicosFotos> getUrlFoto(String medcod) {
		return MedicosFotosRepository.findFotos(medcod);
	}

	
	
	private String generateUniqueFileName(String originalFileName, String subDirectory) {
        // Obtener extensión del archivo
        String fileExtension = getFileExtension(originalFileName);
        
        // Generar timestamp actual
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        
        // Generar parte aleatoria única
        String randomPart = UUID.randomUUID().toString().substring(0, 8);
        
        // Extraer el ID del médico de la subDirectory (asumiendo formato "doctors/0030")
        String doctorId = subDirectory.replace("doctors/", "");
        
        // Crear nombre único
        return String.format("doctor_%s_%s_%s.%s", 
            doctorId, timestamp, randomPart, fileExtension);
    }

    /**
     * Obtiene la extensión de un archivo.
     */
    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "jpg"; // Extensión por defecto
        }
        
        // Extraer extensión (última parte después del último punto)
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            String extension = fileName.substring(lastDotIndex + 1).toLowerCase();
            
            // Validar extensiones permitidas
            String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "bmp", "webp"};
            for (String allowed : allowedExtensions) {
                if (allowed.equals(extension)) {
                    return extension;
                }
            }
        }
        
        return "jpg"; // Extensión por defecto si no es válida
    }
	
	
}