package com.gestorclinica.GenerarCita.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProgramacionMedicosTurnoHabilitado implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8949557072824626456L;

    private String medcod;
    private String medico;
    private String sercod;
    private String servicio;
    private LocalDate fecha;
    private String horaCita;
    private String horaCitaCita;
    private Boolean estado;

}