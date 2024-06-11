package ar.edu.unq.po2.App;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaminandoTest {
	
	private Caminando caminando;
    private AppUser app;
    private Modo modo;

    @BeforeEach
    void setUp() {
        caminando = new Caminando();
        app = mock(AppUser.class);
        modo = mock(Modo.class);

        when(app.getModo()).thenReturn(modo);
    }

    @Test
    void testManejando() {
        caminando.manejando(app);

        verify(modo, times(1)).alertaFin(app);
        verify(app, times(1)).setEstado(any(Manejando.class));
    }

    @Test
    void testCaminando() {
        caminando.caminando(app);

        // No debería hacer nada, así que simplemente verificamos que no haya interacciones.
        verifyNoInteractions(app);
    }

    @Test
    void testCambiarEstado() {
        caminando.cambiarEstado(app);

        verify(app, times(1)).setEstado(any(Manejando.class));
    }

}
