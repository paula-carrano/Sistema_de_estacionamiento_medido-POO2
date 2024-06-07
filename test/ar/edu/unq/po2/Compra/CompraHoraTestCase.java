package ar.edu.unq.po2.Compra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.SEM.PuntoDeVenta;

public class CompraHoraTestCase {

	CompraHora compra;
	PuntoDeVenta punto;
	
	//SetUp.
	@BeforeEach
	public void setUp() {
		punto = mock(PuntoDeVenta.class);
		compra = new CompraHora(45, 52, punto);
	}

	@Test
	public void testGetters() {
		assertEquals(52, compra.getCantHoras());
	}
}
