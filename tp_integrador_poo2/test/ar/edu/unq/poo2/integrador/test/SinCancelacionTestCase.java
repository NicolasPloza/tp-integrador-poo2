package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.SinCancelacion;

class SinCancelacionTestCase {
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSeCalculaElCostoDeCancelacionDeLaPolitica() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 11, 11));
		Inmueble hotel = mock(Inmueble.class);
		when(hotel.getPrecioDefault()).thenReturn(200.0);
		
		SinCancelacion sinCancelacion = new SinCancelacion();
		
		assertEquals(400.0, sinCancelacion.costo(reserva, hotel));
	}
}