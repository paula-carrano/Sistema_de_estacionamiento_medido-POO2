package ar.edu.unq.po2.Suscripcion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;


public class EntidadTest {

    @Test
    public void testActualizar() {
        Entidad entidadMock = mock(Entidad.class);
        entidadMock.actualizar();
        verify(entidadMock).actualizar();
    }
}
