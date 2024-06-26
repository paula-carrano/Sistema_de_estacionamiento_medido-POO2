package ar.edu.unq.po2.Estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Punto.Punto;
import ar.edu.unq.po2.SEM.SEM;

class EAplicacionTest {

	private EAplicacion est;
	private AppUser app;
	private Punto punto;
	private SEM sistema;
	
	@BeforeEach
	void setUp() throws Exception {
		punto = mock(Punto.class);
		app = mock(AppUser.class);
		sistema = mock(SEM.class);
		
		when(app.getSistema()).thenReturn(sistema);
		
		est = new EAplicacion("ABC123", app, punto);
	}

	//VIGENCIA
	@Test
	void testEstacionamientoVigente() {
		
		est.setHoraInicio(LocalTime.of(13,00));
		est.setHoraFin(LocalTime.of(16,00));
		
		assertTrue(est.estaVigente(LocalTime.of(14,00)));
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
	
	
	//FINALIZACION
	@Test
	void testEstacionamientoVigenteFinalizar() {
		//Finaliza y le envia un mensaje a la app para que descuente el costo
		
		est.setHoraInicio(LocalTime.of(13,00));
		est.setHoraFin(LocalTime.of(16,00));
		when(sistema.getPrecioPorHora()).thenReturn(40.0);
		
		assertTrue(est.estaVigente(LocalTime.of(14,00)));
		
		est.finalizar(LocalTime.of(14,00));
		
		assertFalse(est.estaVigente(LocalTime.of(14,00)));
		assertEquals(est.getHoraFin(), LocalTime.of(14, 00));
		assertEquals(est.duracionTotal(), 1);
		assertEquals(est.costoTotal(), 40);
		verify(app).descontarSaldo(40);
	}


	@Test
	void testDuracionTotal() {
		est.setHoraInicio(LocalTime.of(13, 0));
		est.setHoraFin(LocalTime.of(16, 0));
		assertEquals(3, est.duracionTotal());
	}

	@Test
	void testCostoTotal() {
		est.setHoraInicio(LocalTime.of(13, 0));
		est.setHoraFin(LocalTime.of(16, 0));
		when(sistema.getPrecioPorHora()).thenReturn(10.0);
		assertEquals(30.0, est.costoTotal());
	}
	
	@Test
	public void testGetPunto() {
		assertEquals(punto, est.getPunto());
	}
}
