package ar.edu.unq.po2.SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.estacionamiento.Estacionamiento;

class ZonaTest {
	
	private Zona zona;
	private Estacionamiento estacionamiento;
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


	
	@Test
	void testAddEstacionamiento() {
		
		assertTrue(zona.getEstacionamientos().isEmpty());
		
		zona.addEstacionamiento(estacionamiento);
		
		assertEquals(zona.getEstacionamientos().size(), 1);
	}
	
	
	@Test
	void testTienePuntoDeVenta() {
		
		assertFalse(zona.tieneAPuntoDeVenta(puntoDeVenta));
		
		zona.addPuntoDeVenta(puntoDeVenta);
		
		assertTrue(zona.tieneAPuntoDeVenta(puntoDeVenta));
	}

}
