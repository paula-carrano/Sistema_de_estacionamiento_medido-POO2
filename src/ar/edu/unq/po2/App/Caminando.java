package ar.edu.unq.po2.App;

public class Caminando implements Estado {

	@Override
	public void manejando(AppUser app) {
		app.getModo().alertaFin(app);
		this.cambiarEstado(app);
	}

	@Override
	public void caminando(AppUser app) {}

	@Override
	public void cambiarEstado(AppUser app) {
		app.setEstado(new Manejando());
	}

}
