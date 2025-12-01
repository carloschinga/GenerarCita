package com.gestorclinica.GenerarCita.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "view_programacion_medico_deta")
public class Medico {

    // sercod es la clave primaria en este contexto (es el código único)
    @Id
    @Column(name = "medcod")
    private String codigo;

    @Column(name = "mednam")
    private String descripcion;

    @Column(name = "sercod")
    private String codigoServicio;

    // Constructor vacío por defecto
    public Medico() {
    }

    // Constructor con todos los campos
    public Medico(String codigo, String descripcion, String codigoServicio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.codigoServicio=codigoServicio;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }


    // Opcional: toString() para depuración
    @Override
    public String toString() {
        return "Servicio{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", codigoServicio='" + codigoServicio + '\'' +
                '}';
    }
}
