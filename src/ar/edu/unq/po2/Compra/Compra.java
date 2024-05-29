package ar.edu.unq.po2.Compra;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.SEM.PuntoDeVenta;

public abstract class Compra {

	// Atributos.
	private int nroControl;
	private LocalDate fecha = LocalDate.now();
	private LocalTime hora  = LocalTime.now();
	private PuntoDeVenta punto;
	
	// Setters.
	protected void setNroControl(int n) {
		this.nroControl = n;
	}
	
	protected void setPunto(PuntoDeVenta p) {
		this.punto = p;
	}
	
}
