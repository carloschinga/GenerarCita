package com.gestorclinica.GenerarCita.facade;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestorclinica.GenerarCita.model.AutorizacionResponse;
import com.gestorclinica.GenerarCita.model.LoginRequest;
import com.gestorclinica.GenerarCita.service.AuthService;

@Component
public class AuthFacade {

	@Autowired
	private AuthService authService;

	public AutorizacionResponse login(LoginRequest t) {
		AutorizacionResponse response = new AutorizacionResponse();

		List<Map<String, Object>> usuario = authService.login(t.getUsername(), t.getPassword());

		if (usuario.isEmpty()) {
			return null;
		}

		for (Map<String, Object> fila : usuario) {
			response.setLogi((String) fila.get("useusr"));
			response.setGrucod((String) fila.get("grucod"));
			Object siscod = fila.get("siscod");
			response.setSiscod(siscod != null ? ((Number) siscod).intValue() : null);
			response.setNombre((String) fila.get("usenam"));
			
			if(!response.getLogi().isEmpty()) {
				response.setResultado("OK");
			}
		}
		// response.setToken(jwtTokenUtil.generateToken(response));
		return response;
	}

}
