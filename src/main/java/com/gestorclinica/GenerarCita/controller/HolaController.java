package com.gestorclinica.GenerarCita.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
    @GetMapping("/api/hola")
    public String hola() {
        return "Hola mundo desdSpring Boot!";
    }
}
