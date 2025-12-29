package com.gestorclinica.GenerarCita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestorclinica.GenerarCita.model.ParametroCita;

@Repository
public interface ParametroCitaRepository extends JpaRepository<ParametroCita, Integer>{

    @Query(value = "select * from bart_parametro_cita", nativeQuery = true)
    List<ParametroCita> findDistinctParametroCita();

    @Modifying
    @Transactional
    @Query(value = "EXEC sp_bart_parametro_cita_update :codi_param, :nomb_param, :valu_param", nativeQuery = true)
    void updateParametroCita(@Param("codi_param") Integer codiParam, @Param("nomb_param") String nombParam , @Param("valu_param") String valuParam);

}
