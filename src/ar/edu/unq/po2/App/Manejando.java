package ar.edu.unq.po2.App;

public class Manejando implements Estado {

	@Override
	public void manejando(AppUser app) {}

	@Override
	public void caminando(AppUser app) {
		app.getModo().alertaInicio(app);
		this.cambiarEstado(app);
	}

	@Override
	public void cambiarEstado(AppUser app) {
		app.setEstado(new Caminando());
	}

}
