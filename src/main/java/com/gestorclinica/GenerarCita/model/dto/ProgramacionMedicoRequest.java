package com.gestorclinica.GenerarCita.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProgramacionMedicoRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6966214769581494020L;

    private String sercod;
    private String medcod;
    private String fecha;
}
