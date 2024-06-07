package ar.edu.unq.po2.Compra;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraRecarga extends Compra {

	// Atributos.
	private AppUser app;
	private double monto;
	
	// Constructor.
	public CompraRecarga(int nroControl, 
			AppUser app, double monto, 
			PuntoDeVenta punto) {
		this.setHora(LocalTime.now());
		this.setFecha(LocalDate.now());
		this.setNroControl(nroControl);
		this.setApp(app);
		this.setMonto(monto);
		this.setPunto(punto);
	}
	
	// Setters.
	private void setApp(AppUser app) {
		this.app = app;
	}
	
	private void setMonto(double monto) {
		this.monto = monto;
	}
	
	public double getMonto() {
		return(this.monto);
	}
}
