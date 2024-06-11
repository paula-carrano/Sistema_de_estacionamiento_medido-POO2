package ar.edu.unq.po2.App;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManualTest {

	  private AppUser appUserMock;
	    private ServicioNotificacion notificadorMock;
	    private Manual manual;

	    @BeforeEach
	    void setUp() {
	        appUserMock = mock(AppUser.class);
	        notificadorMock = mock(ServicioNotificacion.class);
	        manual = new Manual();
	    }

	    @Test
	    public void testAlertaInicio() {
	        when(appUserMock.consultarVigencia()).thenReturn(false);
	        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

	        manual.alertaInicio(appUserMock);

	        // Verificar que se llamó enviarNotificacion con el mensaje correcto
	        verify(notificadorMock).enviarNotificacion("Debe iniciar un estacionamiento.");
	    }

	    @Test
	    public void testAlertaFin() {
	        when(appUserMock.consultarVigencia()).thenReturn(true);
	        when(appUserMock.getNotificador()).thenReturn(notificadorMock);

	        manual.alertaFin(appUserMock);

	        // Verificar que se llamó enviarNotificacion con el mensaje correcto
	        verify(notificadorMock).enviarNotificacion("Debe finalizar su estacionamiento.");
	    }
}
