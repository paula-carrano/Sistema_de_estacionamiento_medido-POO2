package ar.edu.unq.po2.SEM;
import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.estacionamiento.ECompraPuntual;

public class PuntoDeVenta {

	private SEM sistema;
	
	//Le envia una CompraRecarga al SEM
	public void realizarRecarga(int nroControl, LocalDate fecha, LocalTime hora, int celular, double monto) {
		sistema.addCompra(new CompraRecarga(nroControl, fecha, hora, celular, monto));
	}
	
	
	//Le envia una CompraHoras y un ECompraPuntual al SEM
	public void cobrarEstacionamiento(int nroControl, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int cantHoras, String patente) {
		sistema.addCompra(new CompraHoras(nroControl, fecha, horaInicio, cantHoras));
		sistema.addEstacionamientoAZona(new ECompraPuntual(patente, horaInicio, horaFin, cantHoras));
	}
}
