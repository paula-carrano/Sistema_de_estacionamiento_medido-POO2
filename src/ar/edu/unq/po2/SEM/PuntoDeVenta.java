package ar.edu.unq.po2.SEM;
import java.time.LocalDate;
import java.time.LocalTime;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.ECompraPuntual;

public class PuntoDeVenta {

	private SEM sistema;
	
	
	public PuntoDeVenta(SEM sistema) {
		this.sistema = sistema;
	}
	
	//Le envia una CompraRecarga al SEM
	public void realizarRecarga(int nroControl, int celular, double monto, PuntoDeVenta punto) {
		sistema.addCompra(new CompraRecarga(nroControl, celular, monto, this));
	}
	
	
	//Le envia una CompraHoras y un ECompraPuntual al SEM
	public void cobrarEstacionamiento(int nroControl, LocalTime horaFin, int cantHoras, String patente, PuntoDeVenta punto) {
		sistema.addCompra(new CompraHora(nroControl, cantHoras, this));
		sistema.addEstacionamientoAZona(new ECompraPuntual(patente, horaFin, cantHoras), this);
	}
}
