package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Aceptada;
import ar.edu.unq.poo2.integrador.Pendiente;
import ar.edu.unq.poo2.integrador.Reserva;

class PendienteTestCase {
	
	private Pendiente estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		this.estado = Pendiente.getInstance();
		this.reserva = mock(Reserva.class);
	}

	@Test
	void testSeConcretaUnaReservaPendiente() {
		this.estado.concretar(this.reserva);
		
		verify(this.reserva).notificarReserva();
	}
	
	@Test
	void testSeCancelaUnaReservaPendiente() {
		this.estado.cancelar(this.reserva);
		
		verify(this.reserva, never()).notificarCancelacion();
	}
	
	@Test
	void testSeIndicaSiUnaReservaPendienteEstaAceptada() {
		assertFalse(this.estado.estaAceptada());
	}

}
