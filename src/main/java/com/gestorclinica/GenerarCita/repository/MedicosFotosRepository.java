package com.gestorclinica.GenerarCita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestorclinica.GenerarCita.model.MedicosFotos;

@Repository
public interface MedicosFotosRepository extends JpaRepository<MedicosFotos, Integer> {

	@Query(value = "SELECT m.medfotocod, m.medcod, m.url_foto FROM view_medicos_fotos m WHERE m.medcod = :medcod", nativeQuery = true)
	List<MedicosFotos> findFotos(@Param("medcod") String medcod);
}
