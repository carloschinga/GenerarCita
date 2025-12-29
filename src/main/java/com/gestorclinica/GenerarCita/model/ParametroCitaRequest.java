package com.gestorclinica.GenerarCita.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParametroCitaRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8557882145701104391L;

    private Integer codiParam;
    private String nombParam;
    private String valuParam;

}
