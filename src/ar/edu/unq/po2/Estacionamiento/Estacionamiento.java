package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

import ar.edu.unq.po2.Punto.Punto;

public abstract class Estacionamiento {

	private String patente;
	protected LocalTime horaInicio;
	protected LocalTime horaFin;
	protected Punto punto;
	
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
	
	// Temporal.
	protected void setPunto(Punto punto2) {
		this.punto = punto2;
	}
	
	
	//Getters
		// Temporal.
	public Punto getPunto() {
		return this.punto;
	}
	
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