package com.gestorclinica.GenerarCita.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestorclinica.GenerarCita.model.ParametroCita;
import com.gestorclinica.GenerarCita.repository.ParametroCitaRepository;

@Service
public class ParametroCitaService {

    @Autowired
    private ParametroCitaRepository parametroCitaRepository;

    public List<ParametroCita> obtenerParametroCita() {
        return parametroCitaRepository.findDistinctParametroCita();
    }

    public void updateParametroCita(Integer codiParam,String nombParam ,String valuParam) {
        parametroCitaRepository.updateParametroCita(codiParam, nombParam, valuParam);
    }

}
