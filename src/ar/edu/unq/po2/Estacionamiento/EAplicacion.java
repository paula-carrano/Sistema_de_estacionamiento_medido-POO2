package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

import ar.edu.unq.po2.App.AppUser;

public class EAplicacion extends Estacionamiento{

	private AppUser app;
	
	public EAplicacion(String p, AppUser a) {
		this.setPatente(p);
		this.setApp(a);
		this.setHoraInicio(LocalTime.now());
	}
	
	
	//Setters
	private void setApp(AppUser a) {
		this.app = a;
	}

	//Settea la hora de fin
	@Override
	public void finalizar(LocalTime hora) {
		this.setHoraFin(hora);
	}
}
