package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

public abstract class Estacionamiento {

	private String patente;
	protected LocalTime horaInicio;
	protected LocalTime horaFin;
	
	
	//Setters
	protected void setPatente(String p) {
		this.patente = p;
	}
	
	protected void setHoraInicio(LocalTime h) {
		this.horaInicio = h;
	}
	
	protected void setHoraFin(LocalTime h) {
		this.horaFin = h;
	}
	
	
	//Getters
	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public LocalTime getHoraFin() {
		return this.horaFin;
	}
	
	
	//Indica si esta vigente 
	public boolean estaVigente() {
		LocalTime horaActual = LocalTime.now();
        return horaActual.isAfter(this.getHoraInicio()) && horaActual.isBefore(this.getHoraFin());
	}
	
	
	public void finalizar(LocalTime hora) {
		
	}
}
