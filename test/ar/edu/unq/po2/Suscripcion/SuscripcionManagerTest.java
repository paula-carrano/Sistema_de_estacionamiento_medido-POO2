package ar.edu.unq.po2.Suscripcion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class SuscripcionManagerTest {

    private SuscripcionManager suscripcionManager;
    private List<Entidad> entidadesMock;

    @Before
    public void setUp() {
        suscripcionManager = new SuscripcionManager();
        entidadesMock = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            entidadesMock.add(mock(Entidad.class));
        }
    }

    @Test
    public void testSuscribirEntidad() {
        suscripcionManager.suscribirEntidad(entidadesMock.get(0));
        assertTrue(suscripcionManager.getEntidades().contains(entidadesMock.get(0)));
    }

    @Test
    public void testDesuscribirEntidad() {
        suscripcionManager.suscribirEntidad(entidadesMock.get(0));
        suscripcionManager.suscribirEntidad(entidadesMock.get(1));
        suscripcionManager.desuscribirEntidad(entidadesMock.get(0));
        assertFalse(suscripcionManager.getEntidades().contains(entidadesMock.get(0)));
        assertTrue(suscripcionManager.getEntidades().contains(entidadesMock.get(1)));
    }

    @Test
    public void testNotificar() {
        for (Entidad entidad : entidadesMock) {
            suscripcionManager.suscribirEntidad(entidad);
        }
        suscripcionManager.notificar();
        for (Entidad entidad : entidadesMock) {
            verify(entidad).actualizar();
        }
    }
}