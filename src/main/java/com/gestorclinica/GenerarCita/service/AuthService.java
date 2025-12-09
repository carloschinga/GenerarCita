package com.gestorclinica.GenerarCita.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestorclinica.GenerarCita.repository.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;

	public List<Map<String, Object>> login(String username, String password) {
		return authRepository.loginUsuario(username, password);
	}

}
