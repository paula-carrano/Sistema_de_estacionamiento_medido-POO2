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
    private List<Suscriptor> suscriptoresMock;

    @Before
    public void setUp() {
        suscripcionManager = new SuscripcionManager();
        suscriptoresMock = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            suscriptoresMock.add(mock(Suscriptor.class));
        }
    }

    @Test
    public void testSuscribirEntidad() {
        suscripcionManager.agregarSuscriptor(suscriptoresMock.get(0));
        assertTrue(suscripcionManager.getSuscriptores().contains(suscriptoresMock.get(0)));
    }

    @Test
    public void testDesuscribirEntidad() {
        suscripcionManager.agregarSuscriptor(suscriptoresMock.get(0));
        suscripcionManager.agregarSuscriptor(suscriptoresMock.get(1));
        suscripcionManager.removerSuscriptor(suscriptoresMock.get(0));
        assertFalse(suscripcionManager.getSuscriptores().contains(suscriptoresMock.get(0)));
        assertTrue(suscripcionManager.getSuscriptores().contains(suscriptoresMock.get(1)));
    }

    @Test
    public void testNotificar() {
        for (Suscriptor suscriptor : suscriptoresMock) {
            suscripcionManager.agregarSuscriptor(suscriptor);
        }
        suscripcionManager.notificar();
        for (Suscriptor suscriptor : suscriptoresMock) {
            verify(suscriptor).actualizar();
        }
    }
}