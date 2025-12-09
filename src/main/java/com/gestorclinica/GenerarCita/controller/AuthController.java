package com.gestorclinica.GenerarCita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorclinica.GenerarCita.facade.AuthFacade;
import com.gestorclinica.GenerarCita.model.AutorizacionResponse;
import com.gestorclinica.GenerarCita.model.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthFacade authFacade;
	
	@PostMapping("/login")
	public AutorizacionResponse login(@RequestBody LoginRequest request) {
		return authFacade.login(request);
	}

}
