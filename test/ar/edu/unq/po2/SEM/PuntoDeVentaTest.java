package ar.edu.unq.po2.SEM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.ECompraPuntual;

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
	void testRealizarCompraEnviaMensajeAddCompraYNotificarSaldoASEM() {
		
		verify(sistema, times(0)).addCompra(any(CompraRecarga.class));
		verify(sistema, times(0)).notificarSaldo(app, 500);
		
		punto.realizarRecarga(01, app, 500);
		
		verify(sistema, times(1)).addCompra(any(CompraRecarga.class));
		verify(sistema, times(1)).notificarSaldo(app, 500);
	}
	
	
	@Test
	void testcobrarEstacionamientoEnviaMensajeAddCompraASEM() {
		
		verify(sistema, times(0)).addCompra(any(Compra.class));
		
		punto.cobrarEstacionamiento(02, null, 3, "ABC123");
		
		verify(sistema, times(1)).addCompra(any(Compra.class));
	}
	
	
	@Test
	void testcobrarEstacionamientoEnviaMensajeAddEstacionamientoASEM() {
		
		verify(sistema, times(0)).addEstacionamiento(any(ECompraPuntual.class));
		
		punto.cobrarEstacionamiento(02, null, 3, "ABC123");
		
		verify(sistema, times(1)).addEstacionamiento(any(ECompraPuntual.class));
	}
}
