package com.gestorclinica.GenerarCita.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472283853968193515L;
	private String username;
	private String password;
	
	public LoginRequest() {
		super();
	}


}