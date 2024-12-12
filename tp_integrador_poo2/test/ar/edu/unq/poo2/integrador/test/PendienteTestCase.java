package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Aceptada;
import ar.edu.unq.poo2.integrador.Inquilino;
import ar.edu.unq.poo2.integrador.Pendiente;
import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

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
		Propietario prop = mock(Propietario.class);
		when(this.reserva.getPropietario()).thenReturn(prop);
		
		this.estado.concretar(this.reserva);
		
		
		verify(this.reserva).notificarReserva();
		verify(prop).removerReserva(this.reserva);
		verify(this.reserva).setEstado(Aceptada.getInstance());
		
	}
	
	@Test
	void testSeCancelaUnaReservaPendiente() {
		LocalDate fechaIni = LocalDate.of(2024, 12, 11);
		LocalDate fechaFin = LocalDate.of(2024, 12, 20);		
		Inmueble inmueble =  mock(Inmueble.class);
		Inquilino inquilino = mock(Inquilino.class);
		Propietario propietario = mock(Propietario.class);
		
		when(this.reserva.getInmueble()).thenReturn(inmueble);
		when(this.reserva.getFechaInicio()).thenReturn(fechaIni);
		when(this.reserva.getFechaFin()).thenReturn(fechaFin);
		when(this.reserva.getPotencialInquilino()).thenReturn(inquilino);
		when(this.reserva.getPropietario()).thenReturn(propietario);
		
		//exercise 
		this.estado.cancelar(reserva);
	
		
		verify(this.reserva).notificarCancelacion();
		verify(inmueble).procesarReservasCondicionalesPara(fechaIni, fechaFin);
		assertFalse(this.reserva.esAceptada());
	}
	
	@Test
	void testSeIndicaSiUnaReservaPendienteEstaAceptada() {
		assertFalse(this.estado.estaAceptada());
	}

}
