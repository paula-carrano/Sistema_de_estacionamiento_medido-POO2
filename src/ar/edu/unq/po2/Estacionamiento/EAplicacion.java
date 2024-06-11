package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

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
	
	//Calcula la hora de fin a partir de la hora de inicio y la cantidad de horas compradas
	public LocalTime calcularHoraFin() {
		return this.horaInicio.
				plusHours(this.app.calculoHoraMaxima());
	}


	@Override
	public int costoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int duracionTotal() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void finalizar() {
		// TODO Auto-generated method stub
		
	}
}
