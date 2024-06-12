	package ar.edu.unq.po2.Inspector;
	
	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;
	import java.time.LocalDate;
	import java.time.LocalTime;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.SEM.Zona;

	
	class InfraccionTest {
		private Infraccion infraccion;
	    private Inspector inspector;
	    private Zona zona;
	    private LocalDate fecha;
	    private LocalTime hora;
	    private String patente;

	    @BeforeEach
	    public void setUp() {
	        inspector = new Inspector("Inspector1", null, "Zona1");
	        zona = new Zona(inspector,"Zona 1");
	        fecha = LocalDate.now();
	        hora = LocalTime.now();
	        patente = "ABC123";
	        infraccion = new Infraccion(fecha, hora, inspector, patente, zona);
	    }

	    @Test
	    public void testGetFecha() {
	        assertEquals(fecha, infraccion.getFecha());
	    }

	    @Test
	    public void testGetHora() {
	        assertEquals(hora, infraccion.getHora());
	    }

	    @Test
	    public void testGetInspector() {
	        assertEquals(inspector, infraccion.getInspector());
	    }

	    @Test
	    public void testGetPatente() {
	        assertEquals(patente, infraccion.getPatente());
	    }

	    @Test
	    public void testGetZona() {
	        assertEquals(zona, infraccion.getZona());
	    }
	}
