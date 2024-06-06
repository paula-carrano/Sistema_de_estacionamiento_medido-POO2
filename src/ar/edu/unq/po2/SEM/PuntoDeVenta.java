package ar.edu.unq.po2.SEM;
import java.time.LocalTime;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.ECompraPuntual;

public class PuntoDeVenta {

	private SEM sistema;
	
	//Constructor
	public PuntoDeVenta(SEM s) {
		this.setSistema(s);;
	}
	
	
	//Setters
	private void setSistema(SEM s) {
		this.sistema = s;
	}
	
	
	//Le envia una CompraRecarga al SEM y le envia el mensaje para que notifique el saldo
	public void realizarRecarga(int nroControl, AppUser app, double monto) {
		sistema.addCompra(new CompraRecarga(nroControl, app, monto, this));
		sistema.notificarSaldo(app, monto);
	}
	
	
	//Le envia una CompraHoras y un ECompraPuntual al SEM
	public void cobrarEstacionamiento(int nroControl, LocalTime horaFin, int cantHoras, String patente) {
		sistema.addCompra(new CompraHora(nroControl, cantHoras, this));
		sistema.addEstacionamiento(new ECompraPuntual(patente, horaFin, cantHoras));
	}
}
