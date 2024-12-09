package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Calificable;
import ar.edu.unq.poo2.integrador.Calificacion;
import ar.edu.unq.poo2.integrador.Categoria;
import ar.edu.unq.poo2.integrador.Inquilino;
import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.Sistema;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

class InquilinoTestCase {
	
	private Inquilino inquilino;
	private String nombre;
	private String email;
	private int tel;
	private Sistema sistema;
	private Categoria buenInquilino;
	private Calificacion calificacion;
	private Reserva reservaEnMarDelPlata;
	private Reserva reservaEnCarlosPaz;
	private Reserva reservaEnMisiones;
	private Inmueble casa;
	private Inmueble departamento;
	private Inmueble habitacion;

	@BeforeEach
	void setUp() {
		this.nombre = "matias";
		this.email = "email@email.com";
		this.tel = 244234324;
		this.sistema = mock(Sistema.class);
		this.inquilino = new Inquilino(this.nombre, this.email, this.tel, this.sistema);
		this.buenInquilino = mock(Categoria.class);
		this.calificacion = mock(Calificacion.class);
		this.reservaEnMarDelPlata = mock(Reserva.class);
		this.reservaEnCarlosPaz = mock(Reserva.class);
		this.reservaEnMisiones = mock(Reserva.class);
		this.casa = mock(Inmueble.class);
		this.departamento = mock(Inmueble.class);
		this.habitacion = mock(Inmueble.class);
	}

	@Test
	void testSeAgregaUnaCalificacionParaUnInquilino() {
		when(this.sistema.tieneCategoriaPara(Calificable.INQUILINO, buenInquilino)).thenReturn(true);
		when(this.calificacion.getCategoria()).thenReturn(this.buenInquilino);
		this.inquilino.agregarCalificacion(this.calificacion);
		
		assertEquals(1, this.inquilino.getCalificaciones().size());
		assertTrue(this.inquilino.getCalificaciones().contains(this.calificacion));
	}
	
	@Test
	void testSeAgregaUnaCalificacionDeUnaCategoriaQueNoEsParaUnInquilino() {
		when(this.sistema.tieneCategoriaPara(Calificable.INQUILINO, buenInquilino)).thenReturn(false);
		when(this.calificacion.getCategoria()).thenReturn(this.buenInquilino);
		this.inquilino.agregarCalificacion(this.calificacion);
		
		assertEquals(0, this.inquilino.getCalificaciones().size());
		assertFalse(this.inquilino.getCalificaciones().contains(this.calificacion));
	}
	
	@Test
	void testSeObtieneTodasLasReservasDelInquilino() {
		this.inquilino.agregarReserva(this.reservaEnCarlosPaz);
		this.inquilino.agregarReserva(this.reservaEnMarDelPlata);
		
		assertEquals(2, this.inquilino.getTodasLasReservas().size());
		assertTrue(this.inquilino.getTodasLasReservas().contains(this.reservaEnCarlosPaz));
		assertTrue(this.inquilino.getTodasLasReservas().contains(this.reservaEnMarDelPlata));
	}
	
	@Test
	void testSeObtieneTodasLasReservasFuturasDeUnInquilino() {
		this.inquilino.agregarReserva(this.reservaEnCarlosPaz);
		this.inquilino.agregarReserva(this.reservaEnMarDelPlata);
		this.inquilino.agregarReserva(this.reservaEnMisiones);
		when(this.reservaEnCarlosPaz.getFechaInicio()).thenReturn(LocalDate.now().plusDays(100));
		when(this.reservaEnMarDelPlata.getFechaInicio()).thenReturn(LocalDate.now().plusDays(50));
		when(this.reservaEnMisiones.getFechaInicio()).thenReturn(LocalDate.now().minusDays(10));
		
		assertEquals(2, this.inquilino.getReservasFuturas().size());
		assertTrue(this.inquilino.getReservasFuturas().contains(this.reservaEnCarlosPaz));
		assertTrue(this.inquilino.getReservasFuturas().contains(this.reservaEnMarDelPlata));
		assertFalse(this.inquilino.getReservasFuturas().contains(this.reservaEnMisiones));
	}
	
	@Test
	void testSeObtieneTodasLasReservasDeMarDelPlataDeUnInquilino() {
		this.inquilino.agregarReserva(this.reservaEnCarlosPaz);
		this.inquilino.agregarReserva(this.reservaEnMarDelPlata);
		this.inquilino.agregarReserva(this.reservaEnMisiones);
		when(this.reservaEnCarlosPaz.getInmueble()).thenReturn(this.departamento);
		when(this.reservaEnMarDelPlata.getInmueble()).thenReturn(this.casa);
		when(this.reservaEnMisiones.getInmueble()).thenReturn(this.habitacion);
		when(this.departamento.getCiudad()).thenReturn("Carlos Paz");
		when(this.casa.getCiudad()).thenReturn("Mar del Plata");
		when(this.habitacion.getCiudad()).thenReturn("Posadas");
		
		assertEquals(1, this.inquilino.getReservasDeCiudad("Mar del Plata").size());
		assertTrue(this.inquilino.getReservasDeCiudad("Mar del Plata").contains(this.reservaEnMarDelPlata));
		assertFalse(this.inquilino.getReservasDeCiudad("Mar del Plata").contains(this.reservaEnCarlosPaz));
		assertFalse(this.inquilino.getReservasDeCiudad("Mar del Plata").contains(this.reservaEnMisiones));
	}
	
	@Test
	void testSeObtieneTodasLasCiudadesDeLasReservasDeUnInquilino() {
		this.inquilino.agregarReserva(this.reservaEnCarlosPaz);
		this.inquilino.agregarReserva(this.reservaEnMarDelPlata);
		this.inquilino.agregarReserva(this.reservaEnMisiones);
		when(this.reservaEnCarlosPaz.getInmueble()).thenReturn(this.departamento);
		when(this.reservaEnMarDelPlata.getInmueble()).thenReturn(this.casa);
		when(this.reservaEnMisiones.getInmueble()).thenReturn(this.habitacion);
		when(this.departamento.getCiudad()).thenReturn("Carlos Paz");
		when(this.casa.getCiudad()).thenReturn("Mar del Plata");
		when(this.habitacion.getCiudad()).thenReturn("Posadas");
		
		assertEquals(3, this.inquilino.getCiudadesDeReservas().size());
		assertTrue(this.inquilino.getCiudadesDeReservas().contains("Carlos Paz"));
		assertTrue(this.inquilino.getCiudadesDeReservas().contains("Mar del Plata"));
		assertTrue(this.inquilino.getCiudadesDeReservas().contains("Posadas"));
	}
	
	@Test
	void test_seVerificaQueUnInquilinoSabeResponderLaCantidadDeAlquileresQueTuvo() {
		//setup
		Reserva reservaEnCurso = mock(Reserva.class);
		
		inquilino.agregarReserva(reservaEnCurso);
		inquilino.agregarReserva(reservaEnCarlosPaz);
		inquilino.agregarReserva(reservaEnMarDelPlata);
		inquilino.agregarReserva(reservaEnMisiones);
		when(reservaEnCarlosPaz.estaFinalizada()).thenReturn(true);
		when(reservaEnMarDelPlata.estaFinalizada()).thenReturn(true);
		when(reservaEnMisiones.estaFinalizada()).thenReturn(true);
		when(reservaEnCurso.estaFinalizada()).thenReturn(false);
		
		//exercise
		long cantidadDealquileres =  inquilino.getCantidadDeAlquileres();
		
		//verify
		assertEquals(cantidadDealquileres, 3);
	}
	
	
}
