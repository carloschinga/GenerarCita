package com.gestorclinica.GenerarCita.model.dto;

import java.time.LocalDate;

public interface TurnoProgramacionDias {

    String getServicio();

    String getMedcod();

    String getMedico();

    String getMes();

    LocalDate getFecha();

    Integer getTurrat();

    String getHoraini();

    String getHorafin();

}
