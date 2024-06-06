package ar.edu.unq.po2.App;

import static org.junit.jupiter.api.Assertions.*;
import ar.edu.unq.po2.SEM.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

	private AppUser app;
	private SEM sistema;
	
	@BeforeEach
	void setUp() throws Exception {
		
		app = new AppUser("ABC123", sistema);
	}

	
	@Test
	void testTiene0SaldoRecibeRecargaDe500TieneSaldo500() {
		
		assertEquals(app.getSaldo(), 0);
		
		app.registrarSaldo(500);
		
		assertEquals(app.getSaldo(), 500);
	}

}
