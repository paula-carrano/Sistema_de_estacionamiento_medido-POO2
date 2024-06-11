package ar.edu.unq.po2.App;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Estacionamiento.EAplicacion;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;
import ar.edu.unq.po2.SEM.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

	private AppUser app;
	private SEM sistema;
	private Modo modo;
	private ServicioNotificacion notificador;
	private EAplicacion estacionamiento;
	
	@BeforeEach
	void setUp() throws Exception {
		
		sistema = mock(SEM.class);
		modo = mock(Automatico.class);
		notificador = mock(ServicioNotificacion.class);
		estacionamiento = mock(EAplicacion.class);
		
		app  = new AppUser("ABC123", sistema, modo, notificador);
	}

	
	@Test
	void testTiene0SaldoRecibeRecargaDe500TieneSaldo500() {
		
		assertEquals(app.getSaldo(), 0);
		
		app.registrarSaldo(500);
		
		assertEquals(app.getSaldo(), 500);
	}
	
	
	@Test
    void testFinalizarEstacionamientoSinEstacionamientoVigente() throws Exception {

        List<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();

        when(sistema.estacionamientosVigentes()).thenReturn(estacionamientos);

        app.finalizarEstacionamiento();

        verify(notificador).enviarNotificacion("No existe un estacionamiento para esta patente.");
    }
	
	
	

}
