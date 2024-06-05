package ar.edu.unq.po2.SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.ECompraPuntual;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;

class PuntoDeVentaTest {

	private PuntoDeVenta punto;
	private SEM sistema;
	private AppUser app;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		sistema = mock(SEM.class);
		app = mock(AppUser.class);
		punto = new PuntoDeVenta(sistema);
	}

	
	@Test
	void testRealizarCompraEnviaMensajeAddCompraASEM() {
		
		verify(sistema, times(0)).addCompra(any(Compra.class));
		
		punto.realizarRecarga(01, app, 500, punto);
		
		verify(sistema, times(1)).addCompra(any(Compra.class));
	}
	
	
	@Test
	void testcobrarEstacionamientoEnviaMensajeAddCompraASEM() {
		
		verify(sistema, times(0)).addCompra(any(Compra.class));
		
		punto.cobrarEstacionamiento(0, null, 0, null, punto);
		
		verify(sistema, times(1)).addCompra(any(Compra.class));
	}
	
	
	@Test
	void testcobrarEstacionamientoEnviaMensajeAddEstacionamientoAZonaASEM() {
		
		verify(sistema, times(0)).addEstacionamiento(any(ECompraPuntual.class));
		
		punto.cobrarEstacionamiento(0, null, 0, null, punto);
		
		verify(sistema, times(1)).addEstacionamiento(any(ECompraPuntual.class));
	}
}
