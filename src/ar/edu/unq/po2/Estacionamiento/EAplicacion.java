package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Punto.Punto;

public class EAplicacion extends Estacionamiento{

	private AppUser app;
	
	public EAplicacion(String patente, AppUser app, Punto punto) {
		this.setPatente(patente);
		this.setApp(app);
		this.setHoraInicio(LocalTime.now());
		this.setPunto(punto);
	}
	
	
	//Setters
	private void setApp(AppUser app) {
		this.app = app;
	}

	//Settea la hora de fin y le manda un mensaje a la app para que descuente el saldo 
	@Override
	public void finalizar(LocalTime hora) {
		this.setHoraFin(hora);
		app.descontarSaldo(this.costoTotal());
	}

	public double costoTotal() {
		return this.duracionTotal() * app.getSistema().getPrecioPorHora();
	}


	public int duracionTotal() {
		return (int) this.getHoraInicio().until(this.getHoraFin(), ChronoUnit.HOURS);
	}
}
