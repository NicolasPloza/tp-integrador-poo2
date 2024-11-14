package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Cancelada;
import ar.edu.unq.poo2.integrador.Reserva;

class CanceladaTestCase {
	
	private Cancelada estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		this.estado = Cancelada.getInstance();
		this.reserva = mock(Reserva.class);
	}

	@Test
	void testSeConcretaUnaReservaCancelada() {
		this.estado.concretar(this.reserva);
		
		verify(this.reserva, never()).notificarReserva();;
	}
	
	@Test
	void testSeCancelaUnaReservaCancelada() {
		this.estado.cancelar(this.reserva);
		
		verify(this.reserva, never()).notificarCancelacion();
	}
	
	@Test
	void testSeIndicaSiUnaReservaCanceladaEstaAceptada() {
		assertFalse(this.estado.estaAceptada());
	}

}
