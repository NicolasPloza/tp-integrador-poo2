package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.Intermedia;

class IntermediaTestCase {
	
	Reserva reserva;
	Inmueble hotel;
	Intermedia intermedia;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		hotel = mock(Inmueble.class);
		intermedia = new Intermedia();
	}

	@Test
	void testSeCancelaLaReservaVeinteDiasAntesDeLaFechaDeInicio() {
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 12, 31));
		assertEquals(0.0, intermedia.costo(reserva, hotel));
	}
	
	@Test
	void testSeCancelaLaReservaEnElIntervaloAceptableAntesDeLaFechaDeInicio() {
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 11, 27));
		when(reserva.precioParaFechaElegida()).thenReturn(3000.0);
		assertEquals(1500.0, intermedia.costo(reserva, hotel));
	}
	
	@Test
	void testseCancelaFueraDelIntervaloAceptableAntesDeLaFechaDeInicio() {
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 11, 13));
		when(reserva.precioParaFechaElegida()).thenReturn(3000.0);
		assertEquals(3000.0, intermedia.costo(reserva, hotel));
	}	
}
