package com.gestorclinica.GenerarCita.repository;


import com.gestorclinica.GenerarCita.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, String> {

    // Consulta nativa usando la sintaxis del prompt:
    // SELECT DISTINCT sercod, serdes FROM view_programacion_medico_deta
    @Query(value = "SELECT DISTINCT s.sercod, s.serdes FROM view_programacion_medico_deta s ORDER BY s.serdes ASC",
            nativeQuery = true)
    List<Servicio> findDistinctServiciosNative();

    // Alternativa con JPQL (más idiomático de JPA, pero menos directo para Vistas complejas/DISTINCT):
    // Como la entidad ya mapea a la vista, JpaRepository.findAll() generalmente trae todos los registros.
    // Para asegurar el DISTINCT en JPA, a menudo se usa un DTO o se confía en que los campos (sercod, serdes)
    // al ser mapeados como Entidad, no tendrán duplicados si el 'sercod' es único (@Id).
    // Si la vista *realmente* puede tener sercod duplicados con serdes distintos, solo la consulta nativa es 100% segura.
    // Sin embargo, si asumimos que el campo @Id es único, podemos usar:
    List<Servicio> findAll(); // Esto cargaría todos los registros mapeados a la entidad.
}