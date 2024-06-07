package ar.edu.unq.po2.Inspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.SEM.*;

class InspectorTest {

    private Inspector inspector;
    private SEM sistemaMock;
    private Zona zona;

    @BeforeEach
    public void setUp() {
        sistemaMock = mock(SEM.class);
        zona = new Zona("Zona1");
        inspector = new Inspector("123", zona, sistemaMock);
    }

    @Test
    public void testVerificarEstacionamientoSinEstacionamiento() {
        when(sistemaMock.estacionamientoConVigencia("ABC123")).thenReturn(false);
        assertFalse(inspector.verificarEstacionamiento("ABC123"));
    }

    @Test
    public void testVerificarEstacionamientoConEstacionamiento() {
        when(sistemaMock.estacionamientoConVigencia("ABC123")).thenReturn(true);
        assertTrue(inspector.verificarEstacionamiento("ABC123"));
    }

    @Test
    public void testReportarInfraccion() {
        when(sistemaMock.estacionamientoConVigencia("XYZ789")).thenReturn(false);
        inspector.altaInfraccion("XYZ789");
        verify(sistemaMock).estacionamientoConVigencia("XYZ789");       
       
    }

    @Test
    public void testGetInspectorID() {
        assertEquals("123", inspector.getInspectorID());
    }

    @Test
    public void testGetZona() {
        assertEquals(zona.getZonaID(), inspector.getZonaID());
    }

    @Test
    public void testSetInspectorID() {
        inspector.setInspectorID("456");
        assertEquals("456", inspector.getInspectorID());
    }
}
