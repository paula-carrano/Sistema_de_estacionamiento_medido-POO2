package ar.edu.unq.po2.App;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApagadoTest {

	   private Apagado apagado;
	    private AppUser app;

	    @BeforeEach
	    void setUp() {
	        apagado = new Apagado();
	        app = mock(AppUser.class);
	    }

	    @Test
	    void testManejando() {
	        apagado.manejando(app);

	        // No hace nada, solo verificamos que no hay interacciones.
	        verifyNoInteractions(app);
	    }

	    @Test
	    void testCaminando() {
	        apagado.caminando(app);

	        // No hace nada, solo verificamos que no hay interacciones.
	        verifyNoInteractions(app);
	    }

	    @Test
	    void testCambiarEstado() {
	        apagado.cambiarEstado(app);

	        // No hace nada, solo verificamos que no hay interacciones.
	        verifyNoInteractions(app);
	    }

}
