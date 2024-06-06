package ar.edu.unq.po2.SEM;

import java.util.ArrayList;
import java.util.List;

public class Zona {
	
	//private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	
	
	public Zona(/*Inspector i*/) {
		//this.inspector = inspector;
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	
	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}
	
	
	public void addPuntoDeVenta(PuntoDeVenta p) {
		puntosDeVenta.add(p);
	}
}
