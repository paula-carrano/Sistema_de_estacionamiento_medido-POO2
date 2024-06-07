package ar.edu.unq.po2.Suscripcion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;


public class SuscriptorTest {

    @Test
    public void testActualizar() {
        Suscriptor suscriptorMock = mock(Suscriptor.class);
        suscriptorMock.actualizar();
        verify(suscriptorMock).actualizar();
    }
}
