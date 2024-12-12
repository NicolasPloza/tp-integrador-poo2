package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Aceptada;
import ar.edu.unq.poo2.integrador.Cancelada;
import ar.edu.unq.poo2.integrador.Inquilino;
import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

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

	@Test
	void testSeCancelaUnaReservaAceptada() {
		
		LocalDate fechaIni = LocalDate.of(2024, 12, 11);
		LocalDate fechaFin = LocalDate.of(2024, 12, 20);		
		Inmueble inmueble =  mock(Inmueble.class);
		Inquilino inquilino = mock(Inquilino.class);
		
		when(this.reserva.getInmueble()).thenReturn(inmueble);
		when(this.reserva.getFechaInicio()).thenReturn(fechaIni);
		when(this.reserva.getFechaFin()).thenReturn(fechaFin);
		when(this.reserva.getPotencialInquilino()).thenReturn(inquilino);
		
		//exercise 
		this.estado.cancelar(reserva);
		
		verify(this.reserva).notificarCancelacion();
		verify(inmueble).procesarReservasCondicionalesPara(fechaIni, fechaFin);
		verify(this.reserva).setEstado(Cancelada.getInstance());
	}
	

	@Test
	void testSeIndicaSiUnaReservaAceptadaEstaAceptada() {
		assertTrue(this.estado.estaAceptada());
	}

}
