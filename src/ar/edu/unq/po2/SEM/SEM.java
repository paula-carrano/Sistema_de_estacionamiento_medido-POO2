package ar.edu.unq.po2.SEM;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
	
	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.zonas = new ArrayList<Zona>();
		this.estacionamientos = new ArrayList<Estacionamiento>();
		this.setHoraInicio(LocalTime.of(07,00));
		this.setHoraFin(LocalTime.of(20,00));
		this.setPrecioPorHora(40);
		this.suscripcionManager= new SuscripcionManager();
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

	public void addCompra(Compra compra) {
		compras.add(compra);
	}
	
	public void addZona(Zona zona) {
		zonas.add(zona);
	}
	
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientos.add(estacionamiento);
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

	
	public boolean estacionamientoConVigencia(String patente) {
		Estacionamiento estacionamientoAChequear= estacionamientos.stream().filter(e->e.getPatente().equals(patente)).findFirst().get();		
				return estacionamientoAChequear.estaVigente();
	}

	public Infraccion generarInfraccion(String patente, Inspector inspector) {
		return new Infraccion(LocalDate.now(),LocalTime.now(), inspector, patente, inspector.getZonaID());
	}
}
