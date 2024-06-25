package ar.edu.unq.po2.Suscripcion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ar.edu.unq.po2.SEM.EventoSEM;


public class SuscriptorTest {

	 @Test
	    public void testActualizar() {
	        Suscriptor suscriptorMock = mock(Suscriptor.class);
	        EventoSEM evento = EventoSEM.COMPRA;

	        suscriptorMock.actualizar(evento);
	        verify(suscriptorMock).actualizar(evento);
	    }
}
