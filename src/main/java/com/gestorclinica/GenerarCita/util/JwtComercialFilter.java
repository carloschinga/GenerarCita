package com.gestorclinica.GenerarCita.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtComercialFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;

	@Autowired
	public JwtComercialFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	private static final List<String> PUBLIC_PATHS = List.of("/configuration", "/swagger-ui", "/v3/api-docs",
			"/swagger-resources", "/webjars", "/api/public", "/medico", "/programacionmedicos", "/servicio",
			"/api/auth/login","/gcita", "/css", "/js", "/img", "/", "/index.html");

	private boolean isPublicPath(String path) {
		if (path == null)
			return true;
		for (String p : PUBLIC_PATHS) {
			if (path.startsWith(p))
				return true;
		}
		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String path = request.getServletPath();

		// SALTAR validación JWT para rutas públicas
		if (isPublicPath(path) || "OPTIONS".equalsIgnoreCase(request.getMethod())) {
			filterChain.doFilter(request, response);
			return;
		}

		// Si llegamos aquí es porque la ruta debería estar protegida
		final String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
			response.getWriter().write("Token no proporcionado");
			return;
		}

		String token = authHeader.substring(7);
		if (!jwtUtil.validateToken(token)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
			response.getWriter().write("Token inválido o expirado");
			return;
		}

		String username = jwtUtil.extractUsername(token);
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
				new ArrayList<>());

		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);
	}

}
