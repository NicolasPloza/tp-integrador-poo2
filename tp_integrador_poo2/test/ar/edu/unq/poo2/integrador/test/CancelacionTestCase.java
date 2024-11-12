package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Cancelacion;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

class CancelacionTestCase {
	Reserva reserva;
	Inmueble hotel;
	Cancelacion cancelacion;
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		hotel = mock(Inmueble.class);
		cancelacion = new Cancelacion();
	}

	@Test
	void testSeCancelaLaReservaAntesDelPlazoDeDiezDiasPreviosAlDiaDeInicio() {
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 11, 25));
		when(hotel.getPrecioDefault()).thenReturn(300.0);		
		assertEquals(0, cancelacion.costo(reserva, hotel));
	}
	
	@Test
	void testSeCancelaLaReservaDespuesDelPlazoDeDiezDiasPreviosAlDiaDeInicio() {
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 11, 20));
		when(hotel.getPrecioDefault()).thenReturn(300.0);
		assertEquals(600.0, cancelacion.costo(reserva, hotel));
	}
}
