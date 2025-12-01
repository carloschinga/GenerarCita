package com.gestorclinica.GenerarCita.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ProgramacionMedicoId implements Serializable {

    private String codigoMedico;
    private String pronum;
    private String promes;
    private Date profec;

    public ProgramacionMedicoId() {}

    public ProgramacionMedicoId(String codigoMedico, String pronum, String promes, Date profec) {
        this.codigoMedico = codigoMedico;
        this.pronum = pronum;
        this.promes = promes;
        this.profec = profec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgramacionMedicoId)) return false;
        ProgramacionMedicoId that = (ProgramacionMedicoId) o;
        return Objects.equals(codigoMedico, that.codigoMedico) &&
                Objects.equals(pronum, that.pronum) &&
                Objects.equals(promes, that.promes) &&
                Objects.equals(profec, that.profec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoMedico, pronum, promes, profec);
    }
}
