package ar.edu.unq.po2.SEM;
import java.time.LocalTime;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.ECompraPuntual;

public class PuntoDeVenta {

	private SEM sistema;
	
	
	public PuntoDeVenta(SEM s) {
		this.setSistema(s);;
	}
	
	private void setSistema(SEM s) {
		this.sistema = s;
	}
	
	//Le envia una CompraRecarga al SEM
	public void realizarRecarga(int n, AppUser a, double m, PuntoDeVenta pv) {
		sistema.addCompra(new CompraRecarga(n, a, m, this));
	}
	
	
	//Le envia una CompraHoras y un ECompraPuntual al SEM
	public void cobrarEstacionamiento(int n, LocalTime hf, int c, String p, PuntoDeVenta pv) {
		sistema.addCompra(new CompraHora(n, c, this));
		sistema.addEstacionamiento(new ECompraPuntual(p, hf, c));
	}
}
