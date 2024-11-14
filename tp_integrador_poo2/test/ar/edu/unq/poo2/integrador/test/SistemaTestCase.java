package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Categoria;
import ar.edu.unq.poo2.integrador.Inquilino;
import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.Reserva;
import ar.edu.unq.poo2.integrador.Sistema;
import ar.edu.unq.poo2.integrador.Usuario;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.Servicio;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

class SistemaTestCase {
	Sistema sis;
	Reserva reserva;
	Reserva reserva1;
	Inquilino x;
	Inquilino y;
	Reserva reserva2;
	List<Reserva> reservasDeInquilino;
	Inmueble casa;
	Inmueble hotel;
	Servicio luz;
	TipoInmueble duplex;
	List<Servicio> servicios;
	List<TipoInmueble> tiposInmuebles;
	@BeforeEach
	void setUp() throws Exception {
		sis = new Sistema();
		reserva = mock(Reserva.class);
		reserva1 = mock(Reserva.class);
		y = mock(Inquilino.class);
		x = mock(Inquilino.class);
		reserva2 = mock(Reserva.class);
		reservasDeInquilino = Arrays.asList(reserva,reserva1);
		casa = mock(Inmueble.class);
		hotel = mock(Inmueble.class);
		tiposInmuebles = Arrays.asList(duplex);
		duplex = mock(TipoInmueble.class);
		luz = mock(Servicio.class);
		servicios = Arrays.asList(luz);
	}

	@Test
	void testSeRegistraUnaReserva() {
		sis.registrar(mock(Reserva.class));
		
		assertEquals(1, sis.getReservas().size());
	}
	
	@Test
	void testSeAgregaUnaCategoriaAlSistema() {
		sis.agregarCategoria(mock(Categoria.class));
		
		assertEquals(1, cantidadDeCategoriasDelSistema());
	}
	//LO CREE PARA NO ROMPER EL ENCAPSULAMIENTO
	private int cantidadDeCategoriasDelSistema() {
		return sis.getCategorias().size();
	}
	
	@Test
	void testSeAgregaUnTipoDeInmuebleAlSistema() {
		sis.agregarTipoDeInmueble(mock(TipoInmueble.class));
		
		assertEquals(1, cantidadDeTiposDeInmeublesDeSistema());
	}

	private int cantidadDeTiposDeInmeublesDeSistema() {
		return sis.getTipoDeInmueblesAceptados().size();
	}

	@Test
	void testSeAgregaUnServicioALosServiciosAceptadosDelSistema() {
		sis.agregarServicio(luz);
		
		assertEquals(servicios, sis.getServiciosAceptados());
	}
	
	@Test
	void seObtieneLosNombresDeLosTiposDeInmueblesPermitidos() {
		List<String> tipos = Arrays.asList("Duplex");
		
		sis.agregarTipoDeInmueble(duplex);
		when(duplex.getNombre()).thenReturn("Duplex");
		
		assertEquals(tipos, sis.getNombresDeTiposPermitidos());
	}
	
	@Test
	void seObtieneLosNombresDeLosServiciosPermitidosEnElSistema() {
		List<String> servicios = Arrays.asList("Luz");
		
		sis.agregarServicio(luz);
		when(luz.getNombre()).thenReturn("Luz");
		
		assertEquals(servicios, sis.getNombresDeServiciosPermitidos());
	}
	
	@Test
	void seCancelaUnaReserva() {
		Reserva reserva = mock(Reserva.class);
		Reserva reserva1 = mock(Reserva.class);
		sis.registrar(reserva);
		sis.registrar(reserva1);
		
		sis.cancelarReserva(reserva);
		
		verify(reserva).cancelar();;
	}
	
	@Test 
	void seCuentanLaCantidadDeAlquileresDeUnPropietario() {
		Propietario propietario = mock(Propietario.class);
		when(reserva.estaFinalizada()).thenReturn(true);
		when(reserva.getPropietario()).thenReturn(propietario);
		
		Propietario propietario1 = mock(Propietario.class);
		when(reserva1.getPropietario()).thenReturn(propietario1);
		when(reserva1.estaFinalizada()).thenReturn(false);
		
		sis.añadirUsuario(propietario);
		sis.añadirUsuario(propietario1);
		sis.registrar(reserva);
		sis.registrar(reserva1);
		
		assertEquals(1, sis.cantidadDeAlquileresDePropietario(propietario));
	}
	
	@Test
	void seCuentaLaCantidadDeVecesQueSeAlquiloUnInmueble() {		
		when(reserva.getInmueble()).thenReturn(casa);
		when(reserva.estaFinalizada()).thenReturn(true);
		when(reserva1.getInmueble()).thenReturn(hotel);
		when(reserva1.estaFinalizada()).thenReturn(true);
		
		sis.registrar(reserva);
		sis.registrar(reserva1);
		
		assertEquals(1, sis.cantidadVecesAlquilado(casa));
	}
	
	@Test
	void seObtieneLasReservasDeUnInquilino() {
		when(reserva.getPotencialInquilino()).thenReturn(x);
		when(reserva1.getPotencialInquilino()).thenReturn(x);
		when(reserva2.getPotencialInquilino()).thenReturn(y);
		
		sis.registrar(reserva);
		sis.registrar(reserva1);
		sis.registrar(reserva2);
		
		assertEquals(reservasDeInquilino, sis.reservasDeInquilino(x));
	}
	
	@Test
	void seObtieneLasReservasFuturas() {
		when(reserva.getPotencialInquilino()).thenReturn(x);
		when(reserva1.getPotencialInquilino()).thenReturn(x);
		when(reserva2.getPotencialInquilino()).thenReturn(y);
		when(reserva.estaFinalizada()).thenReturn(false);
		when(reserva1.estaFinalizada()).thenReturn(false);
		when(reserva2.estaFinalizada()).thenReturn(true);
		
		sis.registrar(reserva);
		sis.registrar(reserva1);
		sis.registrar(reserva2);
		
		assertEquals(reservasDeInquilino, sis.reservasDeInquilinoFuturas(x));
	}
	
	@Test
	void SeObtienenLasReservasDeUnInquilinoEnUnaCiudad() {
		when(casa.getCiudad()).thenReturn("Rosario");
		when(hotel.getCiudad()).thenReturn("Buenos Aires");
		
		when(reserva.getInmueble()).thenReturn(casa);
		when(reserva.getPotencialInquilino()).thenReturn(x);
		
		when(reserva1.getInmueble()).thenReturn(casa);
		when(reserva1.getPotencialInquilino()).thenReturn(x);
		
		when(reserva2.getInmueble()).thenReturn(hotel);
		when(reserva2.getPotencialInquilino()).thenReturn(x);
		
		sis.registrar(reserva);
		sis.registrar(reserva1);
		sis.registrar(reserva2);
		
		assertEquals(reservasDeInquilino, sis.reservasDeInquilinoEnCiudad(x, "Rosario"));
	}
	
}
