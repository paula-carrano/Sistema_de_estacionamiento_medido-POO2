package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

import ar.edu.unq.po2.App.AppUser;

public class EAplicacion extends Estacionamiento{

	private AppUser app;
	
	public EAplicacion(String patente, AppUser app) {
		this.setPatente(patente);
		this.setApp(app);
		this.setHoraInicio(LocalTime.now());
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
}
