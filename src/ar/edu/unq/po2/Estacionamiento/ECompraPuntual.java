package ar.edu.unq.po2.Estacionamiento;

import java.time.LocalTime;

import ar.edu.unq.po2.Punto.Punto;

public class ECompraPuntual extends Estacionamiento{
	
	private int horasCompradas;
	
	public ECompraPuntual(String patente, int horasCompradas) {
		this.setPatente(patente);
		this.setHoarsCompradas(horasCompradas);
		this.setHoraInicio(LocalTime.now());
		this.setHoraFin(this.calcularHoraFin());
	}

	//Setters
	private void setHoarsCompradas(int horasCompradas) {
		this.horasCompradas = horasCompradas;
	}
	
	
	//Calcula la hora de fin a partir de la hora de inicio y la cantidad de horas compradas
	public LocalTime calcularHoraFin() {
		return this.horaInicio.plusHours(horasCompradas);
	}

	@Override
	public Double costoTotal() {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public int duracionTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
}