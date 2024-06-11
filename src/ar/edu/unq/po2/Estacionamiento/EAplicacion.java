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

	//Settea la hora de fin
	@Override
	public void finalizar(LocalTime hora) {
		this.setHoraFin(hora);
	}
	
	//Calcula la hora de fin a partir de la hora de inicio y la cantidad max de horas permitidas
	public LocalTime calcularHoraFin() {
		return this.horaInicio.
				plusHours(this.app.calculoHoraMaxima());
	}


	@Override
	public Double costoTotal() {
		return this.duracionTotal()* app.getSistema().getPrecioPorHora();
	}


	@Override
	public int duracionTotal() {
		// TODO Auto-generated method stub
		return (int) this.getHoraInicio().until(this.getHoraFin(), ChronoUnit.HOURS);
	}

}
