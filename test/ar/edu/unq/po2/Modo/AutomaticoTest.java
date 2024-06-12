package ar.edu.unq.po2.Modo;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.App.AppUser;
import ar.edu.unq.po2.Modo.Automatico;
import ar.edu.unq.po2.ServicioNotificacion.ServicioNotificacion;

class AutomaticoTest {
	
    private Automatico automatico;
    private AppUser appUserMock;
    private ServicioNotificacion notificadorMock;
    
    @BeforeEach
    void setUp() {
        appUserMock = mock(AppUser.class);
        notificadorMock = mock(ServicioNotificacion.class);
        automatico = new Automatico();
    }
    
    //ALTERTA INICIO
    @Test
    public void testAlertaInicioSinEstacionamientoVigente() {
    	
        when(appUserMock.consultarVigencia()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        automatico.alertaInicio(appUserMock);

        verify(appUserMock).iniciarEstacionamiento();
        verify(notificadorMock).enviarNotificacion("Estacionamiento iniciado de forma automatica.");
    }
    
    
    @Test
    public void testAlertaInicioConEstacionamientoVigente() {
  
        when(appUserMock.consultarVigencia()).thenReturn(true);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        automatico.alertaInicio(appUserMock);

        verify(appUserMock, never()).iniciarEstacionamiento();
        verify(notificadorMock, never()).enviarNotificacion(anyString());
    }
    
    
    //ALERTA FIN
    @Test
    public void testAlertaFinConEstacionamientoVigenteYEnMismaZona(){
    	
        when(appUserMock.consultarVigencia()).thenReturn(true);
        when(appUserMock.esMismoPuntoDeInicio()).thenReturn(true);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);
        
        automatico.alertaFin(appUserMock);

        // Verifica que se haya llamado finalizarEstacionamiento() y enviarNotificacion()
        verify(appUserMock).finalizarEstacionamiento();
        verify(notificadorMock).enviarNotificacion("Estacionamiento finalizado de forma automatica.");
    }

    
    @Test
    public void testAlertaFinConEstacionamientoVigenteYEnDistintaZona() {
     
        when(appUserMock.consultarVigencia()).thenReturn(true);
        when(appUserMock.esMismoPuntoDeInicio()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        automatico.alertaFin(appUserMock);

        // Verifica que no se haya llamado enviarNotificacion() ni a finalizarEstacionamiento()
        verify(appUserMock, never()).finalizarEstacionamiento();
        verify(notificadorMock, never()).enviarNotificacion(anyString());
    }
   
    
    @Test
    public void testAlertaFinSinEstacionamientoVigenteYEnDistintaZona() {
     
        when(appUserMock.consultarVigencia()).thenReturn(false);
        when(appUserMock.esMismoPuntoDeInicio()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        automatico.alertaFin(appUserMock);

        // Verifica que no se haya llamado enviarNotificacion() ni a finalizarEstacionamiento()
        verify(appUserMock, never()).finalizarEstacionamiento();
        verify(notificadorMock, never()).enviarNotificacion(anyString());
    }
    
    
  
    @Test
    public void testSiNoHayEstacionamientoVigenteNoSeConsultaPorElPunto() {
     
        when(appUserMock.consultarVigencia()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        automatico.alertaFin(appUserMock);
        
        //Verfica que si no hay un estacionamiento vigente, no se consulta por el punto
        verify(appUserMock).consultarVigencia();
        verify(appUserMock, never()).esMismoPuntoDeInicio();
    }
}
