package ar.edu.unq.po2.SEM;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.po2.Estacionamiento.*;
import ar.edu.unq.po2.Compra.*;

public class SEM {
	
	private List<Compra> compras;
	private List<Zona> zonas;
	private List<Estacionamiento> estacionamientos;
	private double precioPorHora;
	private LocalTime horaFin;
	
	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.zonas = new ArrayList<Zona>();
		this.estacionamientos = new ArrayList<Estacionamiento>();
	}

	public List<Zona> getZonas() {
		return zonas;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}
	
	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}

	public void addCompra(Compra c) {
		compras.add(c);
	}
	
	public void addZona(Zona z) {
		zonas.add(z);
	}
	
	public void addEstacionamiento(Estacionamiento e) {
		estacionamientos.add(e);
	}
}
