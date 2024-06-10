package ar.edu.unq.po2.App;

public interface Estado {

	// Protocolo.
	public void manejando(AppUser app);
	public void caminando(AppUser app);
	public void cambiarEstado(AppUser app);
	
}
