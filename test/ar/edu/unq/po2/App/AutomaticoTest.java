package ar.edu.unq.po2.App;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutomaticoTest {
    private AppUser appUserMock;
    private ServicioNotificacion notificadorMock;
    private Automatico automatico;

    @BeforeEach
    void setUp() {
        appUserMock = mock(AppUser.class);
        notificadorMock = mock(ServicioNotificacion.class);
        automatico = new Automatico();
    }
    
    @Test
    public void testAlertaInicio() {
  
        when(appUserMock.consultarVigencia()).thenReturn(false);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        automatico.alertaInicio(appUserMock);

        verify(appUserMock).iniciarEstacionamiento();
        verify(notificadorMock).enviarNotificacion("Estacionamiento iniciado.");
    }
    @Test
    public void testAlertaFin() throws Exception {
        when(appUserMock.consultarVigencia()).thenReturn(true);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);
        automatico.alertaFin(appUserMock);

        // Verifica que se haya llamado finalizarEstacionamiento() y enviarNotificacion()
        verify(appUserMock).finalizarEstacionamiento();
        verify(notificadorMock).enviarNotificacion("Estacionamiento finalizado.");
    }

    @Test
    public void testAlertaFin_ExceptionHandled() throws Exception {
     
        when(appUserMock.consultarVigencia()).thenReturn(true);
        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

        doThrow(new Exception()).when(appUserMock).finalizarEstacionamiento();
        
        automatico.alertaFin(appUserMock);

        // Verifica que se haya llamado finalizarEstacionamiento(), pero no enviarNotificacion() debido a la excepción
        verify(appUserMock).finalizarEstacionamiento();
        verify(notificadorMock, never()).enviarNotificacion(anyString());
    }
}
