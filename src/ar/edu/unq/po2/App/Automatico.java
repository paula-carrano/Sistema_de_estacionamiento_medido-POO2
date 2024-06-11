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
	public void alertaFin(AppUser app){
		if (app.consultarVigencia()) {
			try {
				app.finalizarEstacionamiento();
				app.getNotificador().
				enviarNotificacion("Estacionamiento finalizado.");
			} catch (Exception e) {
				System.err.println("Ocurrió un error al finalizar el estacionamiento: " + e.getMessage());
			}
		}
	}
}
