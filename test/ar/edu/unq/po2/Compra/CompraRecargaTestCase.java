package ar.edu.unq.po2.Compra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraRecargaTestCase {

	CompraRecarga compra;
	AppUser app;
	PuntoDeVenta punto;
	
	@BeforeEach
	public void setUp(){
		punto  = mock(PuntoDeVenta.class);
		app    = mock(AppUser.class);
		compra = new CompraRecarga(32, app, 45d, punto);
	}

	@Test
	public void testGetter() {
		assertEquals(45d, compra.getMonto(), 0.0001);
	}
	
}
