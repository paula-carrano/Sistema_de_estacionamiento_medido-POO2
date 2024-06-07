package ar.edu.unq.po2.Estacionamiento;

import static org.junit.jupiter.api.Assertions.*;

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
	void testEstacionamientoCalculoHoraFin() {
		
		assertEquals(est.getHoraInicio().plusHours(cantHoras), est.calcularHoraFin());
	}
}
