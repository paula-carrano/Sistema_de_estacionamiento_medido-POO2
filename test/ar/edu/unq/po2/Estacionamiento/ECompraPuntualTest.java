package ar.edu.unq.po2.Estacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ECompraPuntualTest {
	
	private ECompraPuntual est;
	private int cantHoras;

	
	@BeforeEach
	void setUp() throws Exception {
		
		cantHoras = 3;
		
		est = new ECompraPuntual("ABC123", cantHoras);
	}
	
	
	@Test
	void testGetPatente() {
		
		assertEquals(est.getPatente(), "ABC123");
	}

	
	@Test
	void testEstacionamientoCalculoHoraFin() {
		
		assertEquals(est.getHoraInicio().plusHours(cantHoras), est.calcularHoraFin());
	}
	
	@Test
	void testEstacionamientoVigenteFinalizarNoHaceNada() {
		
		est.setHoraInicio(LocalTime.of(13,00));
		est.setHoraFin(LocalTime.of(16,00));
		
		assertTrue(est.estaVigente(LocalTime.of(14,00)));
		
		est.finalizar(LocalTime.of(14,00));
		
		assertTrue(est.estaVigente(LocalTime.of(14,00)));
		assertEquals(est.getHoraFin(), LocalTime.of(16, 00));
	}
}
