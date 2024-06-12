package ar.edu.unq.po2.Compra;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.SEM.PuntoDeVenta;

public abstract class Compra {

	
	private int nroControl;
	private LocalDate fecha;
	private LocalTime hora;
	private PuntoDeVenta puntoVenta;
	
	
	// Setters.
	protected void setNroControl(int nroControl) {
		this.nroControl = nroControl;
	}
	
	protected void setPunto(PuntoDeVenta puntoVenta) {
		this.puntoVenta = puntoVenta;
	}
	
	protected void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	protected void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
