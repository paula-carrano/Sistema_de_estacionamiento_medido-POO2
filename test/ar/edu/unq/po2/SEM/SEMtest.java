package ar.edu.unq.po2.SEM;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;

class SEMtest {
	
	private SEM sistema;
	private Zona zonaA;
	private Zona zonaB;
	private Estacionamiento estacionamiento;
	private PuntoDeVenta punto;
	private Compra compra;
	

	
	@BeforeEach
	void setUp() throws Exception {
		
		sistema = new SEM();
		zonaA = mock(Zona.class);
		zonaB = mock(Zona.class);	
	}

	
	@Test
	void testAddZona() {
		
		assertTrue(sistema.getZonas().isEmpty());
		
		sistema.addZona(zonaA);
		
		assertEquals(sistema.getZonas().size(), 1);
	}
	
	
	@Test
	void testAddCompra() {
		
		assertTrue(sistema.getCompras().isEmpty());
		
		sistema.addCompra(compra);
		
		assertEquals(sistema.getCompras().size(), 1);
		
	}
	
	
	@Test
	void testAddEstacionamientoAZonaQueTieneAPunto() {
		
		sistema.addZona(zonaA);
		sistema.addZona(zonaB);
		
		assertTrue(zonaA.getEstacionamientos().isEmpty());
		assertTrue(zonaB.getEstacionamientos().isEmpty());
		
		when(zonaA.tieneAPuntoDeVenta(punto)).thenReturn(true);
		when(zonaB.tieneAPuntoDeVenta(punto)).thenReturn(false);
		
		sistema.addEstacionamientoAZona(estacionamiento, punto);
		
		verify(zonaA, times(1)).addEstacionamiento(estacionamiento);
		verify(zonaB, times(0)).addEstacionamiento(estacionamiento);
	}

}
