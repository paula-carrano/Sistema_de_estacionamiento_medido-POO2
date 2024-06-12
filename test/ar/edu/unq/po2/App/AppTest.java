package ar.edu.unq.po2.App;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Estacionamiento.EAplicacion;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;
import ar.edu.unq.po2.Modo.Automatico;
import ar.edu.unq.po2.Modo.Manual;
import ar.edu.unq.po2.Modo.Modo;
import ar.edu.unq.po2.Punto.Punto;
import ar.edu.unq.po2.SEM.*;
import ar.edu.unq.po2.ServicioNotificacion.ServicioNotificacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


class AppTest {

	private AppUser app;
	private SEM sistema;
	private ServicioNotificacion notificador;
	private EAplicacion estacionamiento;
	private Punto punto;
	private Modo modo;
	private Estado mockEstado;
	
	@BeforeEach
	void setUp() throws Exception {
		modo = mock(Modo.class);
		sistema = mock(SEM.class);
		notificador = mock(ServicioNotificacion.class);
		estacionamiento = mock(EAplicacion.class);
		punto = mock(Punto.class);
		when(sistema.getHoraFin()).thenReturn(LocalTime.of(20, 0));
		app  = new AppUser("ABC123", sistema, notificador, punto);
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
    void testFinalizarEstacionamientoSinEstacionamientoVigente(){
		//No se descuenta saldo de la app y ni se finaliza un estacionamiento
		//Se envia una notificacion como excepcion

        List<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
        app.registrarSaldo(50); 

        when(sistema.estacionamientosVigentes()).thenReturn(estacionamientos);

        app.finalizarEstacionamiento();

        assertEquals(app.getSaldo(), 50);
        verify(sistema, never()).addEstacionamiento(any(EAplicacion.class));
        verify(notificador).enviarNotificacion("No existe un estacionamiento vigente para esta patente.");
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
	
	
	//INICIO DE ESTACIONAMIENTO
	@Test
    void testIniciarEstacionamientoConSaldoSuficienteYSinEstacionamientoVigente() {
		//Se inicia un estacionamiento, se lo manda al sistema y notifica
		app.registrarSaldo(50);
		
		when(app.consultarVigencia()).thenReturn(false); 
        when(sistema.getHoraFin()).thenReturn(LocalTime.of(20,00));
        when(sistema.getPrecioPorHora()).thenReturn(40.0);
        
        ArgumentCaptor<EAplicacion> captor = ArgumentCaptor.forClass(EAplicacion.class);

        app.iniciarEstacionamiento();

        verify(sistema).addEstacionamiento(captor.capture());
        EAplicacion estacionamiento = captor.getValue();
        assertNotNull(estacionamiento);
        
        LocalTime horaInicio = estacionamiento.getHoraInicio().truncatedTo(ChronoUnit.SECONDS);
        LocalTime horaMaximaCalculada = app.calcularHoraMaxima(horaInicio).truncatedTo(ChronoUnit.SECONDS);
        
        verify(notificador).enviarNotificacion(
            " - Hora Inicio: " + horaInicio +
            " - Hora maxima: " + horaMaximaCalculada
        );
    }
	
	@Test
	public void testIniciarEstacionamientoSinEstacionamientoVigenteYSaldoInsuficiente() {
		//
		when(app.consultarVigencia()).thenReturn(true);
	    app.descontarSaldo(-50);

	    app.iniciarEstacionamiento();

	    verify(sistema, never()).addEstacionamiento(any(EAplicacion.class));

	    verify(notificador).enviarNotificacion("Ya hay un estacionamiento vigente.");
	}
	
	
	@Test
	public void testIniciarEstacionamientoConEstacionamientoVigenteYSaldoSuficiente() {
		//No se inicia el estacionamiento y notifica que ya existe un estacioanmiento vigente
		
	    when(app.consultarVigencia()).thenReturn(true);
	    app.registrarSaldo(50);

	    app.iniciarEstacionamiento();

	    verify(sistema, never()).addEstacionamiento(any(EAplicacion.class));
	    verify(notificador).enviarNotificacion("Ya hay un estacionamiento vigente.");
	}
	
	 @Test
	    public void testCalcularHoraMaximaConSaldoSuficiente() {
		 	LocalTime horaActual = LocalTime.of(15, 0);
		 	app.registrarSaldo(80);
		    when(sistema.getPrecioPorHora()).thenReturn(40.0);

		    LocalTime expectedHoraMaxima = horaActual.plusHours(2).truncatedTo(ChronoUnit.SECONDS);
		    LocalTime actualHoraMaxima = app.calcularHoraMaxima(horaActual).truncatedTo(ChronoUnit.SECONDS);

		    assertEquals(expectedHoraMaxima, actualHoraMaxima);
	}

	@Test
	public void testIniciarEstacionamientoConSaldoInsuficienteYSinEstacionamientoVigente() {
		//No se inicia el estacionamiento y notifica que el saldop es insuficiente
		
	    when(app.consultarVigencia()).thenReturn(false);
	    app.registrarSaldo(-5); 

	    app.iniciarEstacionamiento();

	    verify(sistema, never()).addEstacionamiento(any(EAplicacion.class));

	    verify(notificador).enviarNotificacion("No se puede iniciar estacionamiento, saldo insuficiente.");
	}
	
	@Test
    public void testCalcularHoraMaximaExcedeHoraFin() {
		 LocalTime horaFinSistema = LocalTime.of(20, 0);
		 LocalTime horaActual = LocalTime.of(19, 0);

		    when(sistema.getHoraFin()).thenReturn(horaFinSistema);
		    
		    app.registrarSaldo(80);
		    when(sistema.getPrecioPorHora()).thenReturn(40.0);

		    LocalTime actualHoraMaxima = app.calcularHoraMaxima(horaActual);

		    // La hora máxima calculada > hora actual y <= hora de finalización del sistema
		    assertTrue(actualHoraMaxima.isAfter(LocalTime.now()) && !actualHoraMaxima.isAfter(horaFinSistema));
    }
	
	@Test
	public void testCalcularHoraMaximaInsuficienteSaldo() {
		LocalTime horaFinSistema = LocalTime.of(20, 0);

        // Mockear los métodos del sistema
        when(sistema.getHoraFin()).thenReturn(horaFinSistema);
        when(sistema.getPrecioPorHora()).thenReturn(10.0);  // Precio por hora

        // Mockear el saldo actual
        app.registrarSaldo(30.0); // Saldo insuficiente para cubrir hasta las 20:00

        // Obtener la hora actual y asegurar que se usa una hora fija
        LocalTime horaActual = LocalTime.of(15, 0);
        
        // Validar el tiempo dentro del rango esperado
        assertTrue(horaActual.isBefore(horaFinSistema));

        // Ejecutar el método a probar
        LocalTime actualHoraMaxima = app.calcularHoraMaxima(horaActual);

        // Calcular la hora máxima esperada basada en el saldo disponible
        LocalTime expectedHoraMaxima = horaActual.plusHours(3);

        // La hora máxima calculada debe ser igual a la hora esperada
        assertEquals(expectedHoraMaxima, actualHoraMaxima);
    }
	
	@Test
    public void testCalcularHoraMaximaSuficienteSaldo() {
        LocalTime horaFinSistema = LocalTime.of(20, 0);
        LocalTime horaActual = LocalTime.of(15, 0);

        // Mockear los métodos del sistema
        when(sistema.getHoraFin()).thenReturn(horaFinSistema);
        when(sistema.getPrecioPorHora()).thenReturn(10.0);  // Precio por hora

        // Mockear el saldo actual
        app.registrarSaldo(100.0); // Suficiente saldo
        
        // Validar el tiempo dentro del rango esperado
        assertTrue(horaActual.isBefore(horaFinSistema));

        // Ejecutar el método a probar
        LocalTime actualHoraMaxima = app.calcularHoraMaxima(horaActual);

        // La hora máxima debería ser igual a la hora de fin del sistema
        assertEquals(horaFinSistema, actualHoraMaxima);
    }
	
	@Test
    public void testEsMismoPuntoDeInicio() {        
        when(sistema.estacionamientoConPatente("ABC123")).thenReturn(estacionamiento);
        when(estacionamiento.getPunto()).thenReturn(punto);

        assertTrue(app.esMismoPuntoDeInicio());
    }
	
	@Test
    public void testNoEsMismoPuntoDeInicio() {        
        Punto otroPunto = mock(Punto.class);
        when(sistema.estacionamientoConPatente("ABC123")).thenReturn(estacionamiento);
        when(estacionamiento.getPunto()).thenReturn(otroPunto);

        assertFalse(app.esMismoPuntoDeInicio());
    }
	
	@Test
	public void testGetModo() {

		// setUp
		app.setModo(modo);
		
		// verify.
		assertEquals(modo, app.getModo());
	}
	
	@Test
	public void testGetSistema() {
		assertEquals(sistema, app.getSistema());
	}
	
	@Test
	public void testGetNotificador() {		
		assertEquals(notificador, app.getNotificador());
	}

	
    @Test
    public void testApagarAlertas() {
        app.apagarAlertas();
        assertTrue(app.getEstado() instanceof Apagado);
    }

    @Test
    public void testEncenderAlertas() {
        app.encenderAlertas();
        assertTrue(app.getEstado() instanceof Manejando);
    }

    @Test
    public void testCambiarAModoAutomatico() {
        app.cambiarAModoAutomatico();
        assertTrue(app.getModo() instanceof Automatico);
    }

    @Test
    public void testCambiarAModoManual() {
        app.cambiarAModoManual();
        assertTrue(app.getModo() instanceof Manual);
    }
    
    @Test
    public void testDriving() {
    	// setUp.
    	mockEstado = mock(Estado.class);
        
    	// Exsercise.
    	app.setEstado(mockEstado);  // Inyecta el mock en AppUser
        app.driving();
        
        // Verify.
        verify(mockEstado).manejando(app);
    }
    
    @Test
    public void testWalking() {
    	// setUp.
    	mockEstado = mock(Estado.class);
        
    	// Exsercise.
    	app.setEstado(mockEstado);  // Inyecta el mock en AppUser
        app.walking();
        
        // Verify.
        verify(mockEstado).caminando(app);
    }

}
