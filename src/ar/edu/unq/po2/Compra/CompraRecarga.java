package ar.edu.unq.po2.Compra;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraRecarga extends Compra {

	// Atributos.
	private int celular;
	private double monto;
	
	// Constructor.
	public CompraRecarga(int n, int c, double m,
			PuntoDeVenta p) {
		this.setNroControl(n);
		this.setCelular(c);
		this.setMonto(m);
		this.setPunto(p);
	}
	
	// Setters.
	private void setCelular(int c) {
		this.celular = c;
	}
	
	private void setMonto(double m) {
		this.monto = m;
	}


}
