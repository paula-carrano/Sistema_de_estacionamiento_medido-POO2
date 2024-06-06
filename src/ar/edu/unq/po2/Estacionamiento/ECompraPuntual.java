package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

public class ECompraPuntual extends Estacionamiento{
	
	private LocalTime hortaFin;
	private int horasCompradas;
	
	
	public ECompraPuntual(String p, int h) {
		this.setPatente(p);
		this.setHoarsCompradas(h);
		this.setHoraInicio(LocalTime.now());
		this.horaFin();
	}

	//Setters
	private void setHoarsCompradas(int cant) {
		this.horasCompradas = cant;
	}
	
	private void setHoraInicio(LocalTime h) {
		this.horaInicio = h;
	}
	
	
	//Calcula la hora de fin a partir de la hora de inicio y la cantidad de horas compradas
	public LocalTime horaFin() {
		return this.horaInicio.plusHours(horasCompradas);
	}
}