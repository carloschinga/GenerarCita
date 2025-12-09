package com.gestorclinica.GenerarCita.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> loginUsuario(String username, String password) {
		String sql = "EXEC sp_bart_login_validar ?,?";
		return jdbcTemplate.queryForList(sql, username, password);
	}

}
