package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.EstadoReserva;
import ar.edu.unq.poo2.integrador.GestionadorDeNotificaciones;
import ar.edu.unq.poo2.integrador.Inquilino;
import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.MedioDePago;

class ReservaTestCase {
	
	private Reserva reserva;
	private Propietario propietario;
	private Inmueble inmueble;
	private MedioDePago medioDepago;
	private EstadoReserva estado;
	private GestionadorDeNotificaciones gestionador;
	private Inquilino inquilino;

	@BeforeEach
	void setUp() {
		this.inquilino = mock(Inquilino.class);
		this.propietario = mock(Propietario.class);
		this.inmueble = mock(Inmueble.class);
		this.medioDepago = mock(MedioDePago.class);
		this.estado = mock(EstadoReserva.class);
		this.gestionador = mock(GestionadorDeNotificaciones.class);
		this.reserva = new Reserva(this.inquilino, this.inmueble, LocalDate.of(1998, 2, 20), LocalDate.of(2000, 1, 1), this.medioDepago);
	}

	@Test
	void testSeConcretaUnaReserva() {
		this.reserva.setEstado(estado);
		this.reserva.concretar();
		
		verify(this.estado).concretar(this.reserva);
	}
	
	@Test
	void testSeCancelaUnaReserva() {
		this.reserva.setEstado(estado);
		this.reserva.cancelar();
		
		verify(this.estado).cancelar(this.reserva);
	}

	@Test
	void testSeNotificaLaReservaDeUnInmueble() {
		this.reserva.setGestionador(this.gestionador);
		this.reserva.notificarReserva();
		
		verify(this.gestionador).notificarReserva(this.reserva.getEmailDelInquilino());
	}
	
	@Test
	void testSeNotificaLaCancelacionDeUnInmueble() {
		when(this.reserva.getInmueble().getPropietario()).thenReturn(this.propietario);
		when(this.propietario.getEmail()).thenReturn("email@mail.com");
		this.reserva.setGestionador(this.gestionador);
		this.reserva.notificarCancelacion();
		
		verify(this.gestionador).notificarCancelacion(this.reserva.getTipoDeInmueble(), this.reserva.getEmailDelPropietario());
	}

	@Test
	void testSeObtieneElPrecioDeLaReserva() {
		when(this.inmueble.getPrecioDePeriodo(this.reserva.getFechaInicio(), this.reserva.getFechaFin())).thenReturn(4500d);
		this.reserva.precioParaFechaElegida();
		
		verify(this.inmueble).getPrecioDePeriodo(this.reserva.getFechaInicio(), this.reserva.getFechaFin());
	}
	
	@Test
	void testSeIndicaSiUnaReservaAceptadaDelAño2000EstaFinalizada() {
		when(this.estado.estaAceptada()).thenReturn(true);
		this.reserva.setEstado(this.estado);
		
		assertTrue(this.reserva.estaFinalizada());
	}
	
	@Test
	void testSeObtieneAlPropietarioDeLaReserva() {
		when(this.inmueble.getPropietario()).thenReturn(this.propietario);
		Propietario propietario = this.reserva.getPropietario();
		
		assertEquals(propietario, this.propietario);
	}
	
	@Test
	void testSeObtieneAlInmuebledeLaReserva() {
		Inmueble inmueble = this.reserva.getInmueble();
		
		assertEquals(inmueble, this.inmueble);
	}
	
	@Test
	void testUnaReservaSeteaUninquilino() {
		
		this.reserva.setPotenciaInquilino(inquilino);
		
		assertEquals(reserva.getPotencialInquilino(), inquilino);
	}
	
	@Test
	void testUnaReservaSeteaUnMedioDePago() {
		when(inmueble.tieneMedioDePago(medioDepago)).thenReturn(true);
		
		this.reserva.setMedioDePago(medioDepago);;
		
		assertEquals(reserva.getMedioDePago() , medioDepago);
		verify(inmueble).tieneMedioDePago(medioDepago);
	}

}
