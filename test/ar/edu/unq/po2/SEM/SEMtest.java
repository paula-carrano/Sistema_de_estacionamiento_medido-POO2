package ar.edu.unq.po2.SEM;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;
import ar.edu.unq.po2.Inspector.Inspector;

class SEMtest {
	
	private SEM sistema;
	private Zona zona;
	private Estacionamiento estacionamiento;
	private Compra compra;
	private AppUser app;
	private Inspector inspector;
	

	
	@BeforeEach
	void setUp() throws Exception {
		
		estacionamiento = mock(Estacionamiento.class);
		app = mock(AppUser.class);
		inspector = mock(Inspector.class);
		
		sistema = new SEM();
	
	}

	
	@Test
	void testAddZona() {
		
		assertTrue(sistema.getZonas().isEmpty());
		
		sistema.addZona(zona);
		
		assertEquals(sistema.getZonas().size(), 1);
	}
	
	
	@Test
	void testAddCompra() {
		
		assertTrue(sistema.getCompras().isEmpty());
		
		sistema.addCompra(compra);
		
		assertEquals(sistema.getCompras().size(), 1);
	}
	
	
	@Test
	void testAddEstacionamiento() {
		
		assertTrue(sistema.getEstacionamientos().isEmpty());
		
		sistema.addEstacionamiento(estacionamiento);
		
		assertEquals(sistema.getEstacionamientos().size(), 1);
	}
	
	
	@Test
	void testRecibeUnaRecargaYNotificaALaAPP() {
			
		verify(app, times(0)).registrarSaldo(500);
		
		sistema.notificarSaldo(app, 500);
		
		verify(app, times(1)).registrarSaldo(500);
	}
	
	
	@Test
	void testFinalizarTodosLosEstacionamientosVigentes() {
		
		sistema.addEstacionamiento(estacionamiento);
		
		when(estacionamiento.estaVigente(LocalTime.of(20, 00))).thenReturn(true);
		
		assertTrue(sistema.getEstacionamientos().stream().allMatch(e -> e.estaVigente(LocalTime.of(20, 00))));
		
		sistema.finalizarEstacionamientos();
		
		assertTrue(sistema.getEstacionamientos().stream().anyMatch(e -> e.estaVigente(LocalTime.of(20, 00))));
	}
	
	
	@Test
	void testGetterPrecioPorHora() {
		
		assertEquals(40, sistema.getPrecioPorHora());
	}
	
	
	@Test
	void testGenerarInfraccion () {
		
		assertTrue(sistema.getInfracciones().isEmpty());
		
		sistema.generarInfraccion("ABC123", inspector);
		
		assertEquals(sistema.getInfracciones().size(), 1);
	}
	
	
	@Test
	void testVerificarEstacionamientoConVigenciaTrue() {
		
		sistema.addEstacionamiento(estacionamiento);
		
		when(estacionamiento.estaVigente(any(LocalTime.class))).thenReturn(true);
		when(sistema.estacionamientosVigentes().getFirst().getPatente()).thenReturn("ABC123");
		
		assertTrue(sistema.verificarEstacionamientoConVigencia("ABC123"));
	}
	
	
	@Test
	void testVerificarEstacionamientoConVigenciaFalse() {
		
		sistema.addEstacionamiento(estacionamiento);
		
		when(estacionamiento.estaVigente(any(LocalTime.class))).thenReturn(true);
		when(sistema.estacionamientosVigentes().getFirst().getPatente()).thenReturn("ABC123");
		
		assertFalse(sistema.verificarEstacionamientoConVigencia("DEF456"));
	}
	
}
