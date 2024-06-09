package ar.edu.unq.po2.SEM;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.ECompraPuntual;

public class PuntoDeVenta {

	private SEM sistema;
	

	public PuntoDeVenta(SEM sistema) {
		this.setSistema(sistema);
	}
	
	
	//Setters
	private void setSistema(SEM sistema) {
		this.sistema = sistema;
	}
	
	
	//Le envia una CompraRecarga al SEM y le envia el mensaje para que notifique el saldo
	public void realizarRecarga(int nroControl, AppUser app, double monto) {
		sistema.addCompra(new CompraRecarga(nroControl, app, monto, this));
		sistema.notificarSaldo(app, monto);
	}
	
	
	//Le envia una CompraHoras y un ECompraPuntual al SEM
	public void cobrarEstacionamiento(int nroControl, int cantHoras, String patente) {
		sistema.addCompra(new CompraHora(nroControl, cantHoras, this));
		sistema.addEstacionamiento(new ECompraPuntual(patente, cantHoras));
	}
}
