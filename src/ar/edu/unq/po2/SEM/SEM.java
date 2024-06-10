package ar.edu.unq.po2.SEM;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Estacionamiento.*;
import ar.edu.unq.po2.Inspector.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Suscripcion.SuscripcionManager;
import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;

public class SEM {
	
	private List<Compra> compras;
	private List<Zona> zonas;
	private List<Estacionamiento> estacionamientos;
	private double precioPorHora;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private SuscripcionManager suscripcionManager;
	private List<Infraccion> infracciones;
	
	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.zonas = new ArrayList<Zona>();
		this.estacionamientos = new ArrayList<Estacionamiento>();
		this.setHoraInicio(LocalTime.of(07,00));
		this.setHoraFin(LocalTime.of(20,00));
		this.setPrecioPorHora(40);
		this.suscripcionManager= new SuscripcionManager();
		this.infracciones = new ArrayList<Infraccion>();
	}
	
	
	//Setters
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
	public void setPrecioPorHora(double precioPorHora) {
		this.precioPorHora = precioPorHora;
	}
	

	//Getters
	public List<Zona> getZonas() {
		return zonas;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}
	
	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}
	
	public double getPrecioPorHora() {
		return this.precioPorHora;
	}
	
	public List<Infraccion> getInfracciones() {
		return infracciones;
	}
	

	public void addCompra(Compra compra) {
		compras.add(compra);
	}
	
	public void addZona(Zona zona) {
		zonas.add(zona);
	}
	
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		// Agregar filtro de si existe un estacionamiento ya creado.
		estacionamientos.add(estacionamiento);
	}

	//Le envia un mensjae a la app con el saldo recarga
	public void notificarSaldo(AppUser app, double monto) {
		app.registrarSaldo(monto);
	}
	
	//A las 20:00hs finaliza todos los estacionamientos vigentes
	public void finalizarEstacionamientos() {
		this.estacionamientosVigentes().stream()
						.forEach(e -> e.finalizar(horaFin));
	}
	
	public boolean verificarEstacionamientoConVigencia(String patente) {		
		return this.estacionamientosVigentes().stream()
								     		   .anyMatch(e -> e.getPatente() == patente);
	}
	
	//Genera un infraccion y la guarda
	public void generarInfraccion(String patente, Inspector inspector) {
		infracciones.add(new Infraccion(LocalDate.now(),LocalTime.now(), inspector, patente, inspector.getZonaID()));
	}
	
	//Devuelve una lista con todos los estacionamientos vigentes 
	public List<Estacionamiento> estacionamientosVigentes() {
		return estacionamientos.stream()
							   .filter(e -> e.estaVigente(LocalTime.now()))
							   .collect(Collectors.toList());
	}
	
}
