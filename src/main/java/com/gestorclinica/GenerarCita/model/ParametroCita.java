package com.gestorclinica.GenerarCita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bart_parametro_cita")
public class ParametroCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi_param")
    private Integer codiParam;

    @Column(name = "nomb_param")
    private String nombParam;

    @Column(name = "valu_param")
    private String valuParam;

    public ParametroCita(Integer codiParam) {
        super();
        this.codiParam = codiParam;
    }

}
