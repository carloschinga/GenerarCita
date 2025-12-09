package com.gestorclinica.GenerarCita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "view_medicos_fotos")
public class MedicosFotos {

	@Id
	@Column(name = "medfotocod")
	private Integer medfotocod;

	@Column(name = "medcod")
	private String medcod;

	@Column(name = "url_foto")
	private String urlFoto;

	public MedicosFotos(Integer medfotocod) {
		super();
		this.medfotocod = medfotocod;
	}

}
