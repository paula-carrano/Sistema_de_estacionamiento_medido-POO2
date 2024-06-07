	package ar.edu.unq.po2.Inspector;
	
	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;
	
	import java.time.LocalDate;
	import java.time.LocalTime;
	
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	
	import ar.edu.unq.po2.SEM.*;
	
	class InfraccionTest {
	    private Infraccion infraccion;
	    private LocalDate fecha;
	    private LocalTime hora;
	    private Inspector inspectorMock;
	    private String patente;
	    private String zonaID;
	
	    @BeforeEach
	    public void setUp() {
	        fecha = LocalDate.of(2024, 6, 6);
	        hora = LocalTime.of(14, 30);
	        patente = "ABC123";
	        zonaID = "Zona1";
	        // Crear un mock de Inspector
	        inspectorMock = mock(Inspector.class);
	        when(inspectorMock.getInspectorID()).thenReturn("123");
	        when(inspectorMock.getZonaID()).thenReturn("Zona1");
	        infraccion = new Infraccion(fecha, hora, inspectorMock, patente, zonaID);
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
	        assertEquals(inspectorMock, infraccion.getInspector());
	    }
	
	    @Test
	    public void testGetPatente() {
	        assertEquals(patente, infraccion.getPatente());
	    }
	
	    @Test
	    public void testGetZonaID() {
	        assertEquals(zonaID, infraccion.getZonaID());
	    }
	
	    @Test
	    public void testConstructor() {
	        LocalDate fecha = LocalDate.of(2024, 6, 20);
	        LocalTime hora = LocalTime.of(12, 0);
	        
	        // Crear la infracción usando el mock de Inspector
	        Infraccion infraccion = new Infraccion(fecha, hora, inspectorMock, "ABC123", "Zona1");
	
	        // Verificar las propiedades de la infracción
	        assertEquals(fecha, infraccion.getFecha());
	        assertEquals(hora, infraccion.getHora());
	        assertEquals(inspectorMock, infraccion.getInspector());
	        assertEquals("ABC123", infraccion.getPatente());
	        assertEquals("Zona1", infraccion.getZonaID());
	    }
	}
