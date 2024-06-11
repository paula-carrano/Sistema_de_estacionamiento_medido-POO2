package ar.edu.unq.po2.App;

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
		if (app.consultarVigencia()) {
			app.getNotificador().
			enviarNotificacion("Debe finalizar su estacionamiento.");
		}
	}
}
