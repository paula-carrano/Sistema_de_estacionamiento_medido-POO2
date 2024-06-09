package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

public abstract class Estacionamiento {

	private String patente;
	protected LocalTime horaInicio;
	protected LocalTime horaFin;
	
	
	//Setters
	protected void setPatente(String patente) {
		this.patente = patente;
	}
	
	protected void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	protected void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
	
	//Getters
	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public LocalTime getHoraFin() {
		return this.horaFin;
	}
	
	public String getPatente() {
		return patente;
	}
	
	
	//Indica si esta vigente 
	public boolean estaVigente(LocalTime horaActual) {
        return horaActual.isAfter(this.getHoraInicio()) && horaActual.isBefore(this.getHoraFin());
	}
	
	
	public void finalizar(LocalTime hora) {}

}
