package ar.edu.unq.po2.SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.estacionamiento.ECompraPuntual;
import ar.edu.unq.po2.estacionamiento.Estacionamiento;

class PuntoDeVentaTest {

	private SEM sistema;
	private PuntoDeVenta punto;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		sistema = mock(SEM.class);
		punto = new PuntoDeVenta(sistema);
	}

	
	@Test
	void testRealizarCompraEnviaMensajeAddCompraASEM() {
		
		punto.realizarRecarga(0, null, null, 0, 0);
		
		verify(sistema, times(1)).addCompra(any(Compra.class));
	}
	
	
	@Test
	void testcobrarEstacionamientoEnviaMensajeAddCompraASEM() {
		
		punto.cobrarEstacionamiento(0, null, null, null, 0, null);
		
		verify(sistema, times(1)).addCompra(any(Compra.class));
	}
	
	
	@Test
	void testcobrarEstacionamientoEnviaMensajeAddEstacionamientoAZonaASEM() {
		
		punto.cobrarEstacionamiento(0, null, null, null, 0, null);
		
		verify(sistema, times(1)).addEstacionamientoAZona(any(ECompraPuntual.class), eq(punto));
	}
}
