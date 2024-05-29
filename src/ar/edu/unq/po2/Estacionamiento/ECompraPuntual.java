package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

public class ECompraPuntual extends Estacionamiento{
	
	private int horasCompradas;

	public ECompraPuntual(String patente, LocalTime horaFin, int horasCompradas) {
		super(patente, horaFin);
		this.horasCompradas = horasCompradas;
	}
}
