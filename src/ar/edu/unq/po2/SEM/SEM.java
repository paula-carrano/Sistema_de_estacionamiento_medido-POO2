package ar.edu.unq.po2.SEM;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ar.edu.unq.po2.Estacionamiento.*;
import ar.edu.unq.po2.Inspector.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Suscripcion.Suscriptor;
import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;

public class SEM {
	
	private List<Compra> compras;
	private List<Zona> zonas;
	private List<Estacionamiento> estacionamientos;
	private double precioPorHora;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private List<Infraccion> infracciones;
	private List<Suscriptor> suscriptores;
	
	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.zonas = new ArrayList<Zona>();
		this.estacionamientos = new ArrayList<Estacionamiento>();
		this.setHoraInicio(LocalTime.of(07,00));
		this.setHoraFin(LocalTime.of(20,00));
		this.setPrecioPorHora(40);
		this.infracciones = new ArrayList<Infraccion>();
		this.suscriptores= new ArrayList<Suscriptor>();
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
	
	public LocalTime getHoraFin() {
		return horaFin;
	}
	
	// Observer management
	public void addSuscriptor(Suscriptor suscriptor) {
	    suscriptores.add(suscriptor);
	}

	public void removeSuscriptor(Suscriptor suscriptor) {
	    suscriptores.remove(suscriptor);
	}

	private void notifySuscriptores(EventoSEM evento) {
		suscriptores.stream().forEach(suscriptor-> suscriptor.actualizar(evento));
	}
		
	public void addCompra(Compra compra) {
		compras.add(compra);
		notifySuscriptores(EventoSEM.COMPRA);
	}
	
	public void addZona(Zona zona) {
		zonas.add(zona);
	}
	
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientos.add(estacionamiento);
		notifySuscriptores(EventoSEM.INICIO_ESTACIONAMIENTO);
	}

	//Le envia un mensjae a la app con el saldo recarga
	public void notificarSaldo(AppUser app, double monto) {
		app.registrarSaldo(monto);
		notifySuscriptores(EventoSEM.RECARGA_CREDITO);
	}
	
	//A las 20:00hs finaliza todos los estacionamientos vigentes
	public void finalizarEstacionamientos() {
		this.estacionamientosVigentes().stream()
						.forEach(e -> e.finalizar(horaFin));
		notifySuscriptores(EventoSEM.FIN_ESTACIONAMIENTOS);
	}
	
	
	//Indica si la patente pasada por parametros corresponde a un estacionamiento vigente
	public boolean verificarEstacionamientoConVigencia(String patente) {		
		return this.estacionamientosVigentes().stream()
								     		   .anyMatch(e -> e.getPatente() == patente);
	}
	
	
	//Genera un infraccion y la guarda
	public void generarInfraccion(String patente, Inspector inspector) {
		Infraccion infraccion= new Infraccion(LocalDate.now(),LocalTime.now(), inspector, patente, this.buscarZonaDeInspector(inspector));
		infracciones.add(infraccion);
		notifySuscriptores(EventoSEM.NUEVA_INFRACCION);
	}
	
	
	Zona buscarZonaDeInspector(Inspector inspector) {
		return zonas.stream()
                .filter(z -> z != null && z.getZonaID().equals(inspector.getZonaID()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró ninguna zona para el inspector: " + inspector.getInspectorID()));
	}


	//Devuelve una lista con todos los estacionamientos vigentes 
	public List<Estacionamiento> estacionamientosVigentes() {
		return estacionamientos.stream()
							   .filter(e -> e.estaVigente(LocalTime.now()))
							   .collect(Collectors.toList());
	}
	
	
	//Devuelve el estacionamiento con la patente indicada
	public Estacionamiento estacionamientoConPatente(String patente) {
			return this.estacionamientosVigentes().stream()
												  .filter(e -> patente.equals(e.getPatente()))
												  .findFirst()
												  .get();
	}
	
	
	public boolean buscarZonaPorID(String id) {
		return(
			this.zonas.stream().anyMatch
			(z -> z.getZonaID().equals(id))
		);
	}
	
}
