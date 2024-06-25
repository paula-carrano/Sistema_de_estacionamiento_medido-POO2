package ar.edu.unq.po2.SEM;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Compra.*;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;
import ar.edu.unq.po2.Inspector.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Suscripcion.Suscriptor;

class SEMtest {
	
	private SEM sistema;
	private Zona zona;
	private Estacionamiento estacionamiento;
	private Compra compra;
	private AppUser app;
	private Inspector inspector;
	private Suscriptor suscriptor;	

	
	@BeforeEach
	void setUp() throws Exception {
		estacionamiento = mock(Estacionamiento.class);
		app = mock(AppUser.class);
		inspector = mock(Inspector.class);
		suscriptor = mock(Suscriptor.class);
		zona = mock(Zona.class);
		sistema = new SEM();
		sistema.addSuscriptor(suscriptor);
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
		verify(suscriptor, times(1)).actualizar(EventoSEM.COMPRA);
	}
	
	
	@Test
	void testAddEstacionamiento() {
		
		assertTrue(sistema.getEstacionamientos().isEmpty());
		
		sistema.addEstacionamiento(estacionamiento);
		
		assertEquals(sistema.getEstacionamientos().size(), 1);
		verify(suscriptor, times(1)).actualizar(EventoSEM.INICIO_ESTACIONAMIENTO);
		
	}
	
	
	@Test
	void testRecibeUnaRecargaYNotificaALaAPP() {
			
		verify(app, times(0)).registrarSaldo(500);
		
		sistema.notificarSaldo(app, 500);
		
		verify(app, times(1)).registrarSaldo(500);
		verify(suscriptor, times(1)).actualizar(EventoSEM.RECARGA_CREDITO);
	}
	
	
	@Test
	void testFinalizarTodosLosEstacionamientosVigentes() {
		
		sistema.addEstacionamiento(estacionamiento);
		
		when(estacionamiento.estaVigente(LocalTime.of(20, 00))).thenReturn(true);
		
		assertTrue(sistema.getEstacionamientos().stream().allMatch(e -> e.estaVigente(LocalTime.of(20, 00))));
		
		sistema.finalizarEstacionamientos();
		
		assertTrue(sistema.getEstacionamientos().stream().anyMatch(e -> e.estaVigente(LocalTime.of(20, 00))));
		verify(suscriptor, times(1)).actualizar(EventoSEM.FIN_ESTACIONAMIENTOS);
	}
	
	
	@Test
	void testGetterPrecioPorHora() {
		
		assertEquals(40, sistema.getPrecioPorHora());
	}
	
	
	@Test
	void testGenerarInfraccion () {
		
		 assertTrue(sistema.getInfracciones().isEmpty());

		    when(inspector.getZonaID()).thenReturn("zona1");
		    
		    zona = new Zona(inspector, "zona1");
		    sistema.addZona(zona);
		    
		    sistema.generarInfraccion("ABC123", inspector);
		    
		    assertEquals(1, sistema.getInfracciones().size());
		    
		    Infraccion infraccionGenerada = sistema.getInfracciones().get(0);
		    assertEquals("ABC123", infraccionGenerada.getPatente());
		    assertEquals(inspector, infraccionGenerada.getInspector());
		    verify(suscriptor, times(1)).actualizar(EventoSEM.NUEVA_INFRACCION);
	}
	
	
	@Test
	void testVerificarEstacionamientoConVigenciaTrue() {
		
		sistema.addEstacionamiento(estacionamiento);
		
		when(estacionamiento.estaVigente(any(LocalTime.class))).thenReturn(true);
		when(sistema.estacionamientosVigentes().getFirst().getPatente()).thenReturn("ABC123");
		
		assertTrue(sistema.verificarEstacionamientoConVigencia("ABC123"));
	}
	
	
	@Test
	void testVerificarEstacionamientoConVigenciaFalse() {
		
		sistema.addEstacionamiento(estacionamiento);
		
		when(estacionamiento.estaVigente(any(LocalTime.class))).thenReturn(true);
		when(sistema.estacionamientosVigentes().getFirst().getPatente()).thenReturn("ABC123");
		
		assertFalse(sistema.verificarEstacionamientoConVigencia("DEF456"));
	}
	
	@Test
	void testEstacionamientoConPatente() {
		when(estacionamiento.estaVigente(any(LocalTime.class))).thenReturn(true);
	    when(estacionamiento.getPatente()).thenReturn("ABC123");
	    
	    sistema.addEstacionamiento(estacionamiento);

	    assertEquals(estacionamiento, sistema.estacionamientoConPatente("ABC123"));
	}
	
	@Test
	void testBuscarZonaPorID() {
		zona = new Zona(inspector, "zona1");
	    sistema.addZona(zona);
	    
	    assertTrue(sistema.buscarZonaPorID(zona.getZonaID()));
	}
	
	@Test
	void testGetHoraFin() {
	    LocalTime horaEsperada = LocalTime.of(20, 0);
	    LocalTime horaFinObtenida = sistema.getHoraFin();

	    assertEquals(horaEsperada, horaFinObtenida, "La hora de finalización debería ser 20:00");
	}
	
	@Test
	void testRemoveSuscriptor() {
		sistema.addEstacionamiento(estacionamiento);
        verify(suscriptor, times(1)).actualizar(EventoSEM.INICIO_ESTACIONAMIENTO);
        
        sistema.removeSuscriptor(suscriptor);
        sistema.addCompra(compra);
        
        verify(suscriptor,times(0)).actualizar(EventoSEM.COMPRA);
	}
	
	@Test
    void testBuscarZonaDeInspector_ZonaEncontrada() {
		
        when(inspector.getZonaID()).thenReturn("zona1");

        Zona zonaEncontrada = new Zona(inspector, "zona1");
        sistema.getZonas().add(zonaEncontrada);

        Zona zonaResultado = sistema.buscarZonaDeInspector(inspector);

        assertEquals(zonaEncontrada, zonaResultado);
    }

    @Test
    void testBuscarZonaDeInspector_ZonaNoEncontrada() {

        when(inspector.getZonaID()).thenReturn("zona2");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> sistema.buscarZonaDeInspector(inspector));

        assertTrue(exception.getMessage().contains("No se encontró ninguna zona para el inspector: "));
    }
	 
}
