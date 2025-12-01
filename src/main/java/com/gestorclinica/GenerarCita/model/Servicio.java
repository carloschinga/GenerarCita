package com.gestorclinica.GenerarCita.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Usamos @Entity y @Table para mapear a la vista.
// Aunque es una vista, Spring Data JPA la tratará como una tabla para la lectura.
@Entity
@Table(name = "view_programacion_medico_deta")
public class Servicio {

    // sercod es la clave primaria en este contexto (es el código único)
    @Id
    @Column(name = "sercod")
    private String codigo;

    @Column(name = "serdes")
    private String descripcion;

    // Constructor vacío por defecto
    public Servicio() {
    }

    // Constructor con todos los campos
    public Servicio(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
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

    // Opcional: toString() para depuración
    @Override
    public String toString() {
        return "Servicio{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
