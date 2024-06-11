package ar.edu.unq.po2.App;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
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

	//SALDO 
	@Test
	void testTieneSaldo0RecibeRecargaDe500TieneSaldo500() {
		
		assertEquals(app.getSaldo(), 0);
		
		app.registrarSaldo(500);
		
		assertEquals(app.getSaldo(), 500);
	}
	
	@Test
	void testTieneSaldo0DescuentoDe500TieneSaldoNegativo() {
		
		assertEquals(app.getSaldo(), 0);
		
		app.descontarSaldo(500);
		
		assertEquals(app.getSaldo(), -500);
	}
	
	
	//FINALIZACION DE ESTACIONAMIENTO 
	@Test
    void testFinalizarEstacionamientoSinEstacionamientoVigente() throws Exception {
		//No se descuenta saldo de la app y ni se finaliza un estacionamiento
		//Se envia una excepcion

        List<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
        app.registrarSaldo(50); 

        when(sistema.estacionamientosVigentes()).thenReturn(estacionamientos);

        app.finalizarEstacionamiento();

        assertEquals(app.getSaldo(), 50);
        verify(notificador).enviarNotificacion("No existe un estacionamiento para esta patente.");
    }
	
	
	@Test
    void testFinalizarEstacionamientoConEstacionamientoVigente() throws Exception {
		//Se descuenta el saldo y se envia la notificacion de finalizacion
		
        List<Estacionamiento> estacionamientos = new ArrayList<>();
        estacionamientos.add(estacionamiento);
        app.registrarSaldo(50);

        when(sistema.estacionamientoConPatente("ABC123")).thenReturn(estacionamiento);
        when(estacionamiento.getPatente()).thenReturn("ABC123");
        when(estacionamiento.getHoraInicio()).thenReturn(LocalTime.of(9,00));
        when(estacionamiento.getHoraFin()).thenReturn(LocalTime.of(10,00));
        when(estacionamiento.duracionTotal()).thenReturn(1);
        when(estacionamiento.costoTotal()).thenReturn(40.00);
        doAnswer(invocation -> {
            app.descontarSaldo(40.00);  // Descontar el saldo en el mock de finalizar
            return null;
        }).when(estacionamiento).finalizar(any(LocalTime.class));
        
        app.finalizarEstacionamiento();

        assertEquals(app.getSaldo(), 10);
        verify(notificador).enviarNotificacion(
              " - Hora Inicio: " + estacionamiento.getHoraInicio()
            + " - Hora de Finalizacion: " + estacionamiento.getHoraFin()
            + " - Duracion total: " + estacionamiento.duracionTotal()
            + " - Costo total: " + estacionamiento.costoTotal()
        );
    }
}
