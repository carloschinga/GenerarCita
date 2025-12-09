package com.gestorclinica.GenerarCita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestorclinica.GenerarCita.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {

	// Consulta nativa usando la sintaxis del prompt:
	// SELECT DISTINCT sercod, serdes FROM view_programacion_medico_deta
	@Query(value = "select distinct m.medcod, m.mednam, m.sercod from view_programacion_medico_deta m", nativeQuery = true)
	List<Medico> findDistinctMedicosNative();

	@Query(value = "select distinct m.medcod, m.mednam, m.sercod from view_programacion_medico_deta m where m.sercod = :sercod", nativeQuery = true)
	List<Medico> findDistinctMedicosBySercod(@Param("sercod") String sercod);

	// Alternativa con JPQL (más idiomático de JPA, pero menos directo para Vistas
	// complejas/DISTINCT):
	// Como la entidad ya mapea a la vista, JpaRepository.findAll() generalmente
	// trae todos los registros.
	// Para asegurar el DISTINCT en JPA, a menudo se usa un DTO o se confía en que
	// los campos (sercod, serdes)
	// al ser mapeados como Entidad, no tendrán duplicados si el 'sercod' es único
	// (@Id).
	// Si la vista *realmente* puede tener sercod duplicados con serdes distintos,
	// solo la consulta nativa es 100% segura.
	// Sin embargo, si asumimos que el campo @Id es único, podemos usar:
	List<Medico> findAll(); // Esto cargaría todos los registros mapeados a la entidad.
	
	@Modifying
	@Transactional
	@Query(value = "EXEC sp_bart_medicos_fotos_saveOrUpdateFile :medcod, :url_foto", nativeQuery = true)
	void saveOrUpdateMedicoFoto(@Param("medcod") String medcod, @Param("url_foto") String urlFoto);

}