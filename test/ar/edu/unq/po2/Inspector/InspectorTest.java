package ar.edu.unq.po2.Inspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.SEM.*;

class InspectorTest {

    private Inspector inspector;
    private SEM sistemaMock;
    private Zona zonaMock;

    @BeforeEach
    public void setUp() {
        sistemaMock = mock(SEM.class);
        zonaMock = mock(Zona.class);
        when(zonaMock.getZonaID()).thenReturn("Zona1");
        inspector = new Inspector("123", zonaMock, sistemaMock);
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
    public void testAltaInfraccionGeneratesInfraccion() {
        String patente = "XYZ789";
        when(sistemaMock.estacionamientoConVigencia(patente)).thenReturn(false);
        inspector.altaInfraccion(patente);
        verify(sistemaMock).generarInfraccion(patente, inspector);
    }

    @Test
    public void testAltaInfraccionDoesNotGenerateInfraccion() {
        String patente = "ABC123";
        when(sistemaMock.estacionamientoConVigencia(patente)).thenReturn(true);
        inspector.altaInfraccion(patente);
        verify(sistemaMock, never()).generarInfraccion(anyString(), any(Inspector.class));
    }

    @Test
    public void testGetInspectorID() {
        assertEquals("123", inspector.getInspectorID());
    }

    @Test
    public void testGetZona() {
        assertEquals("Zona1", inspector.getZonaID());
    }

    @Test
    public void testSetInspectorID() {
        inspector.setInspectorID("456");
        assertEquals("456", inspector.getInspectorID());
    }
}
