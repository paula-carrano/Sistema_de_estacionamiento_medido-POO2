package ar.edu.unq.po2.SEM;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Inspector.Inspector;

public class Zona {
	
	private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	private String zonaID;
	
	
	public Zona(Inspector inspector, String zonaID) {
		this.setInspector(inspector);
		this.setZonaID(zonaID);
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	
	//Setters
	private void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	
	private void setZonaID(String zonaID) {
		this.zonaID = zonaID;
	}
	
	
	//Getters
	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}
	
	public String getZonaID() {
		return zonaID;
	}
	
	
	public void addPuntoDeVenta(PuntoDeVenta p) {
		puntosDeVenta.add(p);
	}
}
