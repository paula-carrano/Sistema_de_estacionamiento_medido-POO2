package ar.edu.unq.po2.Punto;

public class Punto {

	private int latitud;
	private int longitud;
	
	public Punto (int latitud, int longitud) {
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}

	// Setters.
	private void setLatitud(int latitud2) {
		this.latitud = latitud2;
	}

	private void setLongitud(int longitud2) {
		this.longitud = longitud2;
	}
	
	public boolean esMismoPunto(Punto p) {
		return( this.latitud == p.latitud &&
		this.longitud == p.longitud);
	}

}
