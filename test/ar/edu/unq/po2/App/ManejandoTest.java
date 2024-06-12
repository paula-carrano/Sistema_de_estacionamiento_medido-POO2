package ar.edu.unq.po2.App;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Modo.Modo;

class ManejandoTest {

	private Manejando manejando;
    private AppUser app;
    private Modo modo;

    @BeforeEach
    void setUp() {
        manejando = new Manejando();
        app = mock(AppUser.class);
        modo = mock(Modo.class);

        when(app.getModo()).thenReturn(modo);
    }

    @Test
    void testManejando() {
        manejando.manejando(app);

        // se verifica que no hay interacciones.
        verifyNoInteractions(app);
    }

    @Test
    void testCaminando() {
        manejando.caminando(app);

        verify(modo, times(1)).alertaInicio(app);
        verify(app, times(1)).setEstado(any(Caminando.class));
    }

    @Test
    void testCambiarEstado() {
        manejando.cambiarEstado(app);

        verify(app, times(1)).setEstado(any(Caminando.class));
    }
}
