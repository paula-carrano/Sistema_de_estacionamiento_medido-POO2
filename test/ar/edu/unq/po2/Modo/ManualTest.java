package ar.edu.unq.po2.Modo;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Modo.Manual;
import ar.edu.unq.po2.ServicioNotificacion.ServicioNotificacion;

class ManualTest {
	
	private Manual manual;
	private AppUser appUserMock;
	private ServicioNotificacion notificadorMock;
	
	
	@BeforeEach
	void setUp() {
		appUserMock = mock(AppUser.class);
		notificadorMock = mock(ServicioNotificacion.class);
		manual = new Manual();
	}

	
	//ALERTA INICIO
	@Test
	public void testAlertaInicioSinEstacionamientoVigente() {
	    when(appUserMock.consultarVigencia()).thenReturn(false);
	    when(appUserMock.getNotificador()).thenReturn(notificadorMock);

	    manual.alertaInicio(appUserMock);

	    // Verificar que se llamó enviarNotificacion con el mensaje correcto
	    verify(notificadorMock).enviarNotificacion("Debe iniciar un estacionamiento.");
	}
	
	
	@Test
	public void testAlertaInicioConEstacionamientoVigente() {
		
	    when(appUserMock.consultarVigencia()).thenReturn(true);
	    when(appUserMock.getNotificador()).thenReturn(notificadorMock);

	    manual.alertaInicio(appUserMock);

	    // Verificar que nunca se llamó enviarNotificacion 
	    verify(notificadorMock, never()).enviarNotificacion("Debe iniciar un estacionamiento.");
	}

	
	//ALERTA FIN
	@Test
	public void testAlertaFinConEstacionamientoVigenteYEnMismaZona() {
		
		when(appUserMock.consultarVigencia()).thenReturn(true);
        when(appUserMock.esMismoPuntoDeInicio()).thenReturn(true);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);
        
	    manual.alertaFin(appUserMock);

	    // Verificar que se llamó enviarNotificacion con el mensaje correcto
	    verify(notificadorMock).enviarNotificacion("Debe finalizar su estacionamiento.");
	}
	
	
	@Test
	public void testAlertaFinConEstacionamientoVigenteYEnDistintaZona() {
		
		when(appUserMock.consultarVigencia()).thenReturn(true);
        when(appUserMock.esMismoPuntoDeInicio()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);
        
	    manual.alertaFin(appUserMock);

	    // Verificar que nunca se llamó enviarNotificacion 
	    verify(notificadorMock, never()).enviarNotificacion(anyString());
	}
	
	
	@Test
	public void testAlertaFinSinEstacionamientoVigenteYEnDistintaZona() {
		
		when(appUserMock.consultarVigencia()).thenReturn(false);
        when(appUserMock.esMismoPuntoDeInicio()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);
        
	    manual.alertaFin(appUserMock);

	    // Verificar que nunca se llamó enviarNotificacion 
	    verify(notificadorMock, never()).enviarNotificacion(anyString());
	}
	
	
	@Test
    public void testSiNoHayEstacionamientoVigenteNoSeConsultaPorElPunto() {
     
        when(appUserMock.consultarVigencia()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        manual.alertaFin(appUserMock);
        
        //Verfica que si no hay un estacionamiento vigente, no se consulta por el punto
        verify(appUserMock).consultarVigencia();
        verify(appUserMock, never()).esMismoPuntoDeInicio();
    }
	    
}
