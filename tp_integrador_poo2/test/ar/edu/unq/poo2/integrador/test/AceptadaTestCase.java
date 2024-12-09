package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Aceptada;
import ar.edu.unq.poo2.integrador.Reserva;

class AceptadaTestCase {
	
	private Aceptada estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		this.estado = Aceptada.getInstance();
		this.reserva = mock(Reserva.class);
	}

	@Test
	void testSeConcretaUnaReservaAceptada() {
		this.estado.concretar(this.reserva);
		
		verify(this.reserva, never()).notificarReserva();
	}
/*	
	@Test
	void testSeCancelaUnaReservaAceptada() {
		when(this.reserva.tieneInquilinosEncolados()).thenReturn(false);
		this.estado.cancelar(this.reserva);
		
		verify(this.reserva).notificarCancelacion();
	}
	
	@Test
	void testSeCancelaUnaReservaAceptadaConInquilinosEncolados() {
		when(this.reserva.tieneInquilinosEncolados()).thenReturn(true);
		this.estado.cancelar(this.reserva);
		
		verify(this.reserva).procesarCola();
	}
*/	
	@Test
	void testSeIndicaSiUnaReservaAceptadaEstaAceptada() {
		assertTrue(this.estado.estaAceptada());
	}

}
