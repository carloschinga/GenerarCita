package com.gestorclinica.GenerarCita.model.dto;

import java.time.LocalDate;

public interface ProgramacionMedicoCita {

    String getSercod();

    String getMedcod();

    LocalDate getFecha_cita();

    String getHora_cita();
}
