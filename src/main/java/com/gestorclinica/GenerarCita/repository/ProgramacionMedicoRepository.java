package com.gestorclinica.GenerarCita.repository;

import com.gestorclinica.GenerarCita.model.ProgramacionMedico;
import com.gestorclinica.GenerarCita.model.ProgramacionMedicoId;
import com.gestorclinica.GenerarCita.model.dto.ProgramacionMedicoCita;
import com.gestorclinica.GenerarCita.model.dto.ProgramacionMedicoTurnos;
import com.gestorclinica.GenerarCita.model.dto.TurnoProgramacionDias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgramacionMedicoRepository extends JpaRepository<ProgramacionMedico, ProgramacionMedicoId> {

    // Consulta nativa usando la sintaxis del prompt:
    // SELECT DISTINCT sercod, serdes FROM view_programacion_medico_deta
    @Query(value = "select  pm.* from view_programacion_medico_deta pm",
            nativeQuery = true)
    List<ProgramacionMedico> findDistinctProgramacionMedicosNative();

    @Query(value = "select  pm.* from view_programacion_medico_deta pm where pm.sercod = :sercod",
            nativeQuery = true)
    List<ProgramacionMedico> findDistinctProgramacionMedicosBySercod(@Param("sercod") String sercod);

    // Alternativa con JPQL (más idiomático de JPA, pero menos directo para Vistas complejas/DISTINCT):
    // Como la entidad ya mapea a la vista, JpaRepository.findAll() generalmente trae todos los registros.
    // Para asegurar el DISTINCT en JPA, a menudo se usa un DTO o se confía en que los campos (sercod, serdes)
    // al ser mapeados como Entidad, no tendrán duplicados si el 'sercod' es único (@Id).
    // Si la vista *realmente* puede tener sercod duplicados con serdes distintos, solo la consulta nativa es 100% segura.
    // Sin embargo, si asumimos que el campo @Id es único, podemos usar:
    List<ProgramacionMedico> findAll(); // Esto cargaría todos los registros mapeados a la entidad.

    @Transactional
    @Query(value = "EXEC sp_bart_turno_programacion_listar_dias :xsercod, :xmedcod", nativeQuery = true)
    List<TurnoProgramacionDias> findDiasProgramacion(@Param("xsercod") String xsercod, @Param("xmedcod") String xmedcod);

    @Transactional
    @Query(value = "EXEC sp_bart_turno_programacion_listar :xsercod, :xmedcod, :xfecha", nativeQuery = true)
    List<ProgramacionMedicoTurnos> findTurnosProgramacion(@Param("xsercod") String xsercod, @Param("xmedcod") String xmedcod, @Param("xfecha") String xfecha);

    @Transactional
    @Query(value = "EXEC sp_bart_cita_listar :xsercod, :xmedcod, :xfecha", nativeQuery = true)
    List<ProgramacionMedicoCita> findCitas(@Param("xsercod") String xsercod, @Param("xmedcod") String xmedcod, @Param("xfecha") String xfecha);

}
