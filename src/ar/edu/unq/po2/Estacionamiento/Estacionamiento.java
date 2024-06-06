package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

public abstract class Estacionamiento {

	private String patente;
	protected LocalTime horaInicio;
	
	
	//Setters
	protected void setPatente(String p) {
		this.patente = p;
	}
}
