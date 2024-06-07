package ar.edu.unq.po2.Compra;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraHora extends Compra{

	// Atributos.
	private int cantHoras;
	
	// Constructor.
	public CompraHora(int nroControl, int cantHoras, 
			PuntoDeVenta punto) {
		this.setFecha(LocalDate.now());
		this.setHora(LocalTime.now());
		this.setNroControl(nroControl);
		this.setCantHoras(cantHoras);
		this.setPunto(punto);
	}
	
	// Setter.
	private void setCantHoras(int c) {
		this.cantHoras = c;
	}
}
