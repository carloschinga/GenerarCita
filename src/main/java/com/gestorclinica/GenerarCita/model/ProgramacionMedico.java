package com.gestorclinica.GenerarCita.model;



import javax.persistence.*;
import java.util.Date;

// Usamos @Entity y @Table para mapear a la vista.
// Aunque es una vista, Spring Data JPA la tratará como una tabla para la lectura.
@Entity
@Table(name = "view_programacion_medico_deta")
@IdClass(ProgramacionMedicoId.class)
public class ProgramacionMedico {
    @Column(name = "sercod")
    private String codigo;

    @Id
    @Column(name = "medcod")
    private String codigoMedico;

    @Column(name = "mednam")
    private String medico;

    @Column(name = "serdes")
    private String servicio;

    @Id
    @Column(name = "pronum")
    private String pronum;

    @Id
    @Column(name = "promes")
    private String promes;

    @Id
    @Column(name = "profec")
    private Date profec;

    @Column(name = "fecha")
    private Date fecha;


    @Column(name = "Hora")
    private String hora;

    @Column(name = "descon")
    private String descon;

    @Column(name = "dia")
    private Integer dia;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getPronum() {
        return pronum;
    }

    public void setPronum(String pronum) {
        this.pronum = pronum;
    }

    public String getPromes() {
        return promes;
    }

    public void setPromes(String promes) {
        this.promes = promes;
    }

    public Date getProfec() {
        return profec;
    }

    public void setProfec(Date profec) {
        this.profec = profec;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }



    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescon() {
        return descon;
    }

    public void setDescon(String descon) {
        this.descon = descon;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }
}
