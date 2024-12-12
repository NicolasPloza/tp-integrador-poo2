package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.SinCancelacion;

class SinCancelacionTestCase {
	
	private SinCancelacion politica;
	private Reserva reserva;
	
	@BeforeEach
	void setUp() throws Exception {
		this.reserva = mock(Reserva.class);
		this.politica = new SinCancelacion();
	}

	@Test
	void testSeCalculaElCostoDeCancelacionDeLaPolitica() {
		when(this.reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 12, 12));
		when(this.reserva.precioParaFechaElegida()).thenReturn(3000.0);
		
		assertEquals(3000.0, this.politica.costo(this.reserva));
	}
}