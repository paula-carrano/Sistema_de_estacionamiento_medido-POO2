package ar.edu.unq.po2.Inspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.SEM.*;

class InspectorTest {

    private Inspector inspector;
    private SEM sistemaMock;
    
    @BeforeEach
    public void setUp() {
        sistemaMock = mock(SEM.class);
        inspector = new Inspector("Inspector1", sistemaMock, "Zona1");
    }

    @Test
    public void testAltaInfraccionGeneraInfraccion() {
        String patente = "ABC123";
        when(sistemaMock.verificarEstacionamientoConVigencia(patente)).thenReturn(false);

        inspector.altaInfraccion(patente);

        verify(sistemaMock, times(1)).generarInfraccion(patente, inspector);
    }

    @Test
    public void testAltaInfraccionNoGeneraInfraccionSiTieneEstacionamiento() {
        String patente = "ABC123";
        when(sistemaMock.verificarEstacionamientoConVigencia(patente)).thenReturn(true);

        inspector.altaInfraccion(patente);

        verify(sistemaMock, never()).generarInfraccion(patente, inspector);
    }

    @Test
    public void testVerificarEstacionamiento() {
        String patente = "ABC123";
        when(sistemaMock.verificarEstacionamientoConVigencia(patente)).thenReturn(true);

        boolean resultado = inspector.verificarEstacionamiento(patente);

        assertTrue(resultado);
        verify(sistemaMock, times(1)).verificarEstacionamientoConVigencia(patente);
    }
    
    @Test
    public void testGetInspectorID() {
        String inspectorID = inspector.getInspectorID();

        assertEquals("Inspector1", inspectorID);
    }

    @Test
    public void testGetZonaID() {
        String zonaID = inspector.getZonaID();

        assertEquals("Zona1", zonaID);
    }
}
