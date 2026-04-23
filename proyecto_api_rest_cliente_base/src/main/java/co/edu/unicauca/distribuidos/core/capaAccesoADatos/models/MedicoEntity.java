package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicoEntity {
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private Date createAt;
	private Boolean estado;

	public MedicoEntity() {

	}
}

