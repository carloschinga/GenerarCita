package com.gestorclinica.GenerarCita.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AutorizacionResponse  implements Serializable {
 

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6159053067640739047L;

	private String logi; 
	private Integer siscod; 
	private String nombre;
	private String grucod;
	private String resultado;

}
