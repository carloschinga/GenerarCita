package com.gestorclinica.GenerarCita.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

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
     * @param file El archivo MultipartFile recibido del controlador.
     * @return El nombre único del archivo guardado.
     */
    public String storeFile(MultipartFile file, String subDirectory) {
        // Normaliza el nombre del archivo
        String fileName = Objects.requireNonNull(file.getOriginalFilename());

        try {
            // Concatena la subcarpeta (especialidad/medico) a la ruta base
            Path targetDirectory = this.fileStorageLocation.resolve(subDirectory).normalize();
            Files.createDirectories(targetDirectory); // Asegura que la subcarpeta exista

            Path targetLocation = targetDirectory.resolve(fileName);

            // Copia el archivo al destino
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Devuelve la ruta relativa para ser guardada en la base de datos (ej: cardiologia/ana_castillo.jpg)
            return subDirectory + "/" + fileName;

        } catch (IOException ex) {
            throw new RuntimeException("No se pudo guardar el archivo " + fileName + ". Por favor, inténtelo de nuevo!", ex);
        }
    }

    // Método para servir archivos si es necesario
    // public Resource loadFileAsResource(String fileName) { ... }
}