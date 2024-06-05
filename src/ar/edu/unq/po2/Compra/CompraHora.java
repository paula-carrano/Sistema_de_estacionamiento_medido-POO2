package ar.edu.unq.po2.Compra;

import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraHora extends Compra{

	// Atributos.
	private int cantHoras;
	
	// Constructor.
	public CompraHora(int n, int c, PuntoDeVenta p) {
		this.setNroControl(n);
		this.setCantHoras(c);
		this.setPunto(p);
	}
	
	// Setter.
	private void setCantHoras(int c) {
		this.cantHoras = c;
	}
}
