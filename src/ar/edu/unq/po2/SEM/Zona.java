package ar.edu.unq.po2.SEM;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Estacionamiento.*;

public class Zona {
	
	//private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	private List<Estacionamiento> estacionamientos;
	
	
	public Zona(/*Inspector inspector*/) {
		//this.inspector = inspector;
		this.estacionamientos = new ArrayList<Estacionamiento>();
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}
	
	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}
	
	public void addPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		puntosDeVenta.add(puntoDeVenta);
	}
	
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientos.add(estacionamiento);
	}
	
	//Indica si el punto de venta pasado por àrametros se encuentra en su lista
	public boolean tieneAPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		return this.puntosDeVenta.contains(puntoDeVenta);
	}
}
