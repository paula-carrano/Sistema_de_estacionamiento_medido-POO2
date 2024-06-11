package ar.edu.unq.po2.Punto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PuntoTestCase {

	Punto punto;
	Punto punto2;
	Punto punto3;
	Punto punto4;
	Punto punto5;
	
	
	@BeforeEach
	public void setUp() {
		punto  = new Punto(5, 7);
		punto2 = new Punto(5, 7);
		punto3 = new Punto(55, 77);
		punto4 = new Punto(55, 7);
		punto5 = new Punto(5, 77);
	}
	
	@Test
	public void testEsMismoPuntoVerdadero() {
		assertTrue(punto.esMismoPunto(punto2));
	}
	
	@Test
	public void testEsMismoPuntoFalso1() {
		assertFalse(punto.esMismoPunto(punto3));
	}
	
	@Test
	public void testEsMismoPuntoFalso2() {
		assertFalse(punto.esMismoPunto(punto4));
	}

	@Test
	public void testEsMismoPuntoFalso3() {
		assertFalse(punto.esMismoPunto(punto5));
	}
	
}
