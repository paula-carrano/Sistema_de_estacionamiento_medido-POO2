package ar.edu.unq.po2.SEM;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;

class SEMtest {
	
	private SEM sistema;
	private Zona zona;
	private Estacionamiento estacionamiento;
	private Compra compra;
	

	
	@BeforeEach
	void setUp() throws Exception {
		
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

}
