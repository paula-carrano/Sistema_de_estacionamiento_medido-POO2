package ar.edu.unq.po2.Modo;

import ar.edu.unq.po2.App.AppUser;

public class Manual implements Modo {

	@Override
	public void alertaInicio(AppUser app){
		if (!app.consultarVigencia()) {
			app.getNotificador().
			enviarNotificacion("Debe iniciar un estacionamiento.");
		}
	}

	@Override
	public void alertaFin(AppUser app) {
		if (app.consultarVigencia() && app.esMismoPuntoDeInicio()) {
			app.getNotificador().
			enviarNotificacion("Debe finalizar su estacionamiento.");
		}
	}
}
