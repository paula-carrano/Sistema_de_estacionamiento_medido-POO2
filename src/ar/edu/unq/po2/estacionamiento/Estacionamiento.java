package ar.edu.unq.po2.estacionamiento;

import java.time.LocalTime;

public abstract class Estacionamiento {

	private String patente;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	public Estacionamiento(String patente, LocalTime horaFin) {
		this.patente = patente;
		this.horaInicio = LocalTime.now();
		this.horaFin = horaFin;
	}
}
