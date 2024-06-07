package ar.edu.unq.po2.SEM;

import java.util.ArrayList;
import java.util.List;

public class Zona {
	
	//private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	private String zonaID;
	
	
	public Zona(/*Inspector i*/String zonaID) {
		//this.inspector = inspector;
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
		this.zonaID=zonaID;
	}
	
	
	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}
	
	
	public void addPuntoDeVenta(PuntoDeVenta p) {
		puntosDeVenta.add(p);
	}
	
	public String getZonaID() {
		return zonaID;
	}
}
