package ar.edu.unq.po2.App;

public class Automatico implements Modo {

	@Override
	public void alertaInicio(AppUser app) {
		if (!app.consultarVigencia()) {
			app.iniciarEstacionamiento();
			app.getNotificador().
			enviarNotificacion("Estacionamiento iniciado.");
		}
	}

	@Override
	public void alertaFin(AppUser app) {
		if (app.consultarVigencia()) {
			app.finalizarEstacionamiento();
			app.getNotificador().
			enviarNotificacion("Estacionamiento finalizado.");
		}
	}



}
