package ar.edu.unq.po2.Inspector;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.SEM.Zona;

public class Infraccion {
    
	private LocalDate fecha;
    private LocalTime hora;
    private Inspector inspector;
    private String patente;
    private Zona zona;

    
    public Infraccion(LocalDate fecha, LocalTime hora, Inspector inspector, String patente, Zona zona) {
        this.setFecha(fecha);
    	this.setHora(hora);
    	this.setInspector(inspector);
    	this.setPatente(patente);
    	this.zona(zona);
    }

    //Getters and setters
    private void zona(Zona zona) {
		this.zona=zona;
	}

	private void setPatente(String patente) {
		this.patente=patente;		
	}


	private void setInspector(Inspector inspector) {
		this.inspector=inspector;
	}


	private void setHora(LocalTime hora) {
		this.hora=hora;
		
	}

	private void setFecha(LocalDate fecha) {
		this.fecha=fecha;
		
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public String getPatente() {
		return patente;
	}
	
	public Zona getZona() {
		return zona;
	}

}
