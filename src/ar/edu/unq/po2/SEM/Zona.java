package ar.edu.unq.po2.SEM;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Punto.Punto;

public class Zona {
	
	private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	private String zonaID;
	
	public Zona(Inspector inspector, String zonaID) {
		this.setInspector(inspector);
		this.setZonaID(zonaID);
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
		this.setInspectorZonaID(zonaID);
	}
	


	//Setters
	private void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	
	private void setZonaID(String zonaID) {
		this.zonaID = zonaID;
	}

	private void setInspectorZonaID(String zonaID) {
		inspector.setZonaID(zonaID);
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
