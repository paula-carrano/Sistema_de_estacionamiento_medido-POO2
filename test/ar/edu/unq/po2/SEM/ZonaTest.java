package ar.edu.unq.po2.SEM;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ZonaTest {
	
	private Zona zona;
	private PuntoDeVenta puntoDeVenta;
	

	@BeforeEach
	void setUp() throws Exception {
		
		zona = new Zona();
	}
	
	
	@Test
	void testAddPuntoDeVenta() {
		
		assertTrue(zona.getPuntosDeVenta().isEmpty());
		
		zona.addPuntoDeVenta(puntoDeVenta);
		
		assertEquals(zona.getPuntosDeVenta().size(), 1);
	}
}
