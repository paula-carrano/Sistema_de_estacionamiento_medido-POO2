package ar.edu.unq.po2.Compra;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.SEM.PuntoDeVenta;

public abstract class Compra {

	// Atributos.
	private int nroControl;
	private LocalDate fecha;
	private LocalTime hora;
	private PuntoDeVenta punto;
	
	// Setters.
	protected void setNroControl(int nroControl) {
		this.nroControl = nroControl;
	}
	
	protected void setPunto(PuntoDeVenta punto) {
		this.punto = punto;
	}
	
}
