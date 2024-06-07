package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

public class ECompraPuntual extends Estacionamiento{
	
	private int horasCompradas;
	
	
	public ECompraPuntual(String p, int h) {
		this.setPatente(p);
		this.setHoarsCompradas(h);
		this.setHoraInicio(LocalTime.now());
		this.setHoraFin(this.calcularHoraFin());
	}

	//Setters
	private void setHoarsCompradas(int cant) {
		this.horasCompradas = cant;
	}
	
	
	//Calcula la hora de fin a partir de la hora de inicio y la cantidad de horas compradas
	public LocalTime calcularHoraFin() {
		return this.horaInicio.plusHours(horasCompradas);
	}
}