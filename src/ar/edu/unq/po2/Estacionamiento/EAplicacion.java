package ar.edu.unq.po2.Estacionamiento;

import ar.edu.unq.po2.App.AppUser;

public class EAplicacion extends Estacionamiento{

	private AppUser app;
	
	public EAplicacion(String p, AppUser a) {
		this.setPatente(p);
		this.setApp(a);
	}
	
	
	//Setters
	private void setApp(AppUser a) {
		this.app = a;
	}
}
