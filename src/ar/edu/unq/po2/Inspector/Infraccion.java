package ar.edu.unq.po2.Inspector;

import java.time.LocalDate;
import java.time.LocalTime;

public class Infraccion {
    private LocalDate fecha;
    private LocalTime hora;
    private Inspector inspector;
    private String patente;
    private String zonaID;

    public Infraccion(LocalDate fecha, LocalTime hora, Inspector inspector, String patente, String zonaID) {
        this.fecha = fecha;
        this.hora = hora;
        this.inspector = inspector;
        this.patente = patente;
        this.zonaID = zonaID;
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

	public String getZonaID() {
		return zonaID;
	}
}
