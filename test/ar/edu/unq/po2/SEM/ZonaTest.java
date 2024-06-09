package ar.edu.unq.po2.SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Inspector.Inspector;


class ZonaTest {
	
	private Zona zona;
	private PuntoDeVenta puntoDeVenta;
	private Inspector inspector;
	

	@BeforeEach
	void setUp() throws Exception {
		
		inspector = new Inspector("123", mock(SEM.class)); 
        zona = new Zona(inspector, "z1");
        puntoDeVenta = new PuntoDeVenta(mock(SEM.class));
	}
	
	
	@Test
	void testGetZonaID() {
		
		assertEquals(zona.getZonaID(), "z1");
	}
	
	
	@Test
	void testAddPuntoDeVenta() {
		
		assertTrue(zona.getPuntosDeVenta().isEmpty());
		
		zona.addPuntoDeVenta(puntoDeVenta);
		
		assertEquals(zona.getPuntosDeVenta().size(), 1);
	}
	
	@Test
    void testSetInspectorZonaID() {
        assertEquals("z1", inspector.getZonaID());
    }
}
