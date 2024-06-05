package ar.edu.unq.po2.Compra;

import ar.edu.unq.po2.SEM.AppUser;
import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraRecarga extends Compra {

	// Atributos.
	private AppUser app;
	private double monto;
	
	// Constructor.
	public CompraRecarga(int n, AppUser a, double m, PuntoDeVenta p) {
		this.setNroControl(n);
		this.setApp(a);
		this.setMonto(m);
		this.setPunto(p);
	}
	
	// Setters.
	private void setApp(AppUser a) {
		this.app = a;
	}
	
	private void setMonto(double m) {
		this.monto = m;
	}
}
