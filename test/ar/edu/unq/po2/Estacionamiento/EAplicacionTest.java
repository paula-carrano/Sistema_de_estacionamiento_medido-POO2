package ar.edu.unq.po2.Estacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.App.AppUser;

class EAplicacionTest {

	private EAplicacion est;
	private AppUser app;
	
	@BeforeEach
	void setUp() throws Exception {
		est = new EAplicacion("ABC123", app);
	}

	@Test
	void testEstacionamientoVigente() {
		
		est.setHoraInicio(LocalTime.of(13,00));
		est.setHoraFin(LocalTime.of(16,00));
		
		assertTrue(est.estaVigente(LocalTime.of(14,00)));
	}
	
	@Test
	void testEstacionamientoNoVigente() {
		
		est.setHoraInicio(LocalTime.of(13,00));
		est.setHoraFin(LocalTime.of(16,00));
		
		assertFalse(est.estaVigente(LocalTime.of(18,00)));
	}
	
	
	@Test
	void testEstacionamientoVigenteFinalizar() {
		
		est.setHoraInicio(LocalTime.of(13,00));
		est.setHoraFin(LocalTime.of(16,00));
		
		assertTrue(est.estaVigente(LocalTime.of(14,00)));
		
		est.finalizar(LocalTime.of(14,00));
		
		assertFalse(est.estaVigente(LocalTime.of(14,00)));
		assertEquals(est.getHoraFin(), LocalTime.of(14, 00));
	}


	@Test
	void testEstacionamientoInicioExactoNoVigente() {
	    est.setHoraInicio(LocalTime.of(13, 0));
	    est.setHoraFin(LocalTime.of(16, 0));
	
	    assertFalse(est.estaVigente(LocalTime.of(13, 0)));
	}

	@Test
	void testEstacionamientoFinExactoNoVigente() {
	    est.setHoraInicio(LocalTime.of(13, 0));
	    est.setHoraFin(LocalTime.of(16, 0));
	
	    assertFalse(est.estaVigente(LocalTime.of(16, 0)));
	}

	@Test
	void testEstacionamientoAntesDeInicioNoVigente() {
	    est.setHoraInicio(LocalTime.of(13, 0));
	    est.setHoraFin(LocalTime.of(16, 0));
	
	    assertFalse(est.estaVigente(LocalTime.of(12, 59)));
	}

	@Test
	void testEstacionamientoDespuesDeFinNoVigente() {
	    est.setHoraInicio(LocalTime.of(13, 0));
	    est.setHoraFin(LocalTime.of(16, 0));
	
	    assertFalse(est.estaVigente(LocalTime.of(16, 1)));
	}
}
