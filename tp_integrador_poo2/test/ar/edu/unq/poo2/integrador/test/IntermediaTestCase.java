package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Intermedia;

class IntermediaTestCase {
	
	private Reserva reserva;
	private Intermedia intermedia;
	
	@BeforeEach
	void setUp() throws Exception {
		this.reserva = mock(Reserva.class);
		this.intermedia = new Intermedia();
	}

	@Test
	void testSeCancelaLaReservaConMasDeVeinteDiasAntesDeLaFechaDeInicio() {
		when(this.reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(25));
		when(this.reserva.precioParaFechaElegida()).thenReturn(3000.0);
		
		assertEquals(0.0, this.intermedia.costo(this.reserva));
	}
	
	@Test
	void testSeCancelaLaReservaEnElIntervaloAceptableAntesDeLaFechaDeInicio() {
		when(this.reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(15));
		when(this.reserva.precioParaFechaElegida()).thenReturn(3000.0);
		
		assertEquals(1500.0, this.intermedia.costo(this.reserva));
	}
	
	@Test
	void testseCancelaFueraDelIntervaloAceptableAntesDeLaFechaDeInicio() {
		when(this.reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(5));
		when(this.reserva.precioParaFechaElegida()).thenReturn(3000.0);
		
		assertEquals(3000.0, this.intermedia.costo(this.reserva));
	}	
}
