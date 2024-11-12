package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Cancelacion;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

class CancelacionTestCase {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.precioParaFechaElegida()).thenReturn(3000.0);
		Inmueble hotel = mock(Inmueble.class);
		when(hotel.getPrecioDefault()).thenReturn(300.0);
		
		Cancelacion cancelacion = new Cancelacion();
		
		assertEquals(600.0, cancelacion.costo(reserva, hotel));
	}

}
