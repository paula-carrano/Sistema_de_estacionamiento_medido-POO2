package ar.edu.unq.po2.SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;

class SEMtest {
	
	private SEM sistema;
	private Zona zona;
	private Estacionamiento estacionamiento;
	private Compra compra;
	private AppUser app;
	

	
	@BeforeEach
	void setUp() throws Exception {
		
		estacionamiento = mock(Estacionamiento.class);
		app = mock(AppUser.class);
		
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
		
		when(estacionamiento.estaVigente()).thenReturn(true);
		
		assertTrue(sistema.getEstacionamientos().stream().allMatch(e -> e.estaVigente()));
		
		sistema.finalizarEstacionamientos();
		
		assertTrue(sistema.getEstacionamientos().stream().anyMatch(e -> e.estaVigente()));
	}

}
