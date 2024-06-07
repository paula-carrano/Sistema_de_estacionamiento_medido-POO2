package ar.edu.unq.po2.SEM;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.po2.Estacionamiento.*;
import ar.edu.unq.po2.App.AppUser;
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
		this.horaFin = LocalTime.of(20, 00);
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

	//Le envia un mensjae a la app con el saldo recarga
	public void notificarSaldo(AppUser app, double monto) {
		app.registrarSaldo(monto);
	}
	
	//A las 8:00pm finaliza todos los estacionamientos vigentes
	public void finalizarEstacionamientos() {
		estacionamientos.stream()
						.filter(e -> e.estaVigente())
						.forEach(e -> e.finalizar(horaFin));
	}
}
