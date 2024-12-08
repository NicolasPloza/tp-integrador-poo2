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
	
	private Reserva reserva;
	private Cancelacion cancelacion;
	private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		this.reserva = mock(Reserva.class);
		this.inmueble = mock(Inmueble.class);
		this.cancelacion = new Cancelacion();
	}

	@Test
	void testSeCancelaLaReservaAntesDelPlazoDeDiezDiasPreviosAlDiaDeInicio() {
		when(this.reserva.getInmueble()).thenReturn(this.inmueble);
		when(this.reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(15));
		when(this.reserva.getInmueble().getPrecioDefault()).thenReturn(300.0);
		
		assertEquals(0, this.cancelacion.costo(this.reserva));
	}
	
	@Test
	void testSeCancelaLaReservaDespuesDelPlazoDeDiezDiasPreviosAlDiaDeInicio() {
		when(this.reserva.getInmueble()).thenReturn(this.inmueble);
		when(this.reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(5));
		when(this.reserva.getInmueble().getPrecioDefault()).thenReturn(300.0);
		
		assertEquals(600.0, this.cancelacion.costo(this.reserva));
	}
}
