package ar.edu.unq.po2.Compra;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraHora extends Compra{

	
	private int cantHoras;
	
	
	public CompraHora(int control, int horas, 
			PuntoDeVenta punto) {
		this.setFecha(LocalDate.now());
		this.setHora(LocalTime.now());
		this.setNroControl(control);
		this.setCantHoras(horas);
		this.setPunto(punto);
	}
	
	// Setter.
	private void setCantHoras(int c) {
		this.cantHoras = c;
	}
	
	// Getter.
	public int getCantHoras() {
		return(this.cantHoras);
	}
}
