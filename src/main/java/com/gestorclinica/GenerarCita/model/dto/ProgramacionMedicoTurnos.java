package com.gestorclinica.GenerarCita.model.dto;

import java.time.LocalDate;

public interface ProgramacionMedicoTurnos {

    String getServicio();

    String getMedico();

    LocalDate getFecha();

    String getHoraCita();
}
