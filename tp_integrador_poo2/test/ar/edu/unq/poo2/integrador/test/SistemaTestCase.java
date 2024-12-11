package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Calificable;
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
	
	private Sistema sis;
	private Reserva reserva;
	private Reserva reserva1;
	private Inquilino x;
	private Inquilino y;
	private Reserva reserva2;
	private List<Reserva> reservasDeInquilino;
	private Inmueble casa;
	private Inmueble hotel;
	private Servicio luz;
	private TipoInmueble duplex;
	private List<Servicio> servicios;
	private List<TipoInmueble> tiposInmuebles;
	private Calificable propietario;
	private Categoria buenServicio;
	
	@BeforeEach
	void setUp() throws Exception {
		this.sis = new Sistema();
		this.reserva = mock(Reserva.class);
		this.reserva1 = mock(Reserva.class);
		this.y = mock(Inquilino.class);
		this.x = mock(Inquilino.class);
		this.reserva2 = mock(Reserva.class);
		this.reservasDeInquilino = Arrays.asList(reserva,reserva1);
		this.casa = mock(Inmueble.class);
		this.hotel = mock(Inmueble.class);
		this.tiposInmuebles = Arrays.asList(duplex);
		this.duplex = mock(TipoInmueble.class);
		this.luz = mock(Servicio.class);
		this.servicios = Arrays.asList(luz);
		this.propietario = mock(Calificable.class);
		this.buenServicio = mock(Categoria.class);
	}

	@Test
	void testSeRegistraUnaReserva() {
		sis.registrar(mock(Reserva.class));
		
		assertEquals(1, sis.getReservas().size());
	}
	
	@Test
	void testSeRegistraUnInmuebleAceptadaPorUnSistema() {
		Inmueble inmueble = mock(Inmueble.class);
		when(inmueble.getTipoDeInmueble()).thenReturn(this.duplex);
		when(inmueble.getServicio()).thenReturn(Arrays.asList(this.luz));
		this.sis.agregarTipoDeInmueble(this.duplex);
		this.sis.agregarServicio(this.luz);
		this.sis.registrarInmueble(inmueble);
		
		assertEquals(1, sis.getInmuebles().size());
		assertTrue(this.sis.getInmuebles().contains(inmueble));
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoPuedaRegistrarseEnUnSistema() {
		Inmueble inmueble = mock(Inmueble.class);
		this.sis.agregarTipoDeInmueble(this.duplex);
		this.sis.agregarServicio(this.luz);
		this.sis.registrarInmueble(inmueble);
		
		assertEquals(0, sis.getInmuebles().size());
		assertFalse(this.sis.getInmuebles().contains(inmueble));
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoPuedaSerAceptadoPorNoTenerLosServiciosAceptadosPorUnSistema() {
		Inmueble inmueble = mock(Inmueble.class);
		Servicio agua = mock(Servicio.class);
		when(inmueble.getTipoDeInmueble()).thenReturn(this.duplex);
		when(inmueble.getServicio()).thenReturn(Arrays.asList(agua));
		this.sis.agregarTipoDeInmueble(this.duplex);
		this.sis.agregarServicio(this.luz);
		this.sis.registrarInmueble(inmueble);
		
		assertEquals(0, sis.getInmuebles().size());
		assertFalse(this.sis.getInmuebles().contains(inmueble));
	}
	
	@Test
	void testSeAgregaUnaCategoriaAlSistema() {
		sis.agregarCategoria(this.propietario, this.buenServicio);
		
		assertEquals(1, this.sis.getCategoriasPara(this.propietario).size());
		assertTrue(this.sis.tieneCategoriaPara(this.propietario, this.buenServicio));
	}
	
	@Test
	void testSeAgregaUnTipoDeInmuebleAlSistema() {
		sis.agregarTipoDeInmueble(mock(TipoInmueble.class));
		
		assertEquals(1, sis.getTipoDeInmueblesAceptados().size());
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
    void testSeObtieneLosInquilinosQueMasHanAlquiladoEnUnSistema() {
        Propietario propietario = mock(Propietario.class);
        Inquilino inquilinoUno = mock(Inquilino.class);
        Inquilino inquilinoDos = mock(Inquilino.class);
        Inquilino inquilinoTres = mock(Inquilino.class);
        Inquilino inquilinoCuatro = mock(Inquilino.class);
        Inquilino inquilinoCinco = mock(Inquilino.class);
        Inquilino inquilinoSeis = mock(Inquilino.class);
        Inquilino inquilinoSiete = mock(Inquilino.class);
        Inquilino inquilinoOcho = mock(Inquilino.class);
        Inquilino inquilinoNueve = mock(Inquilino.class);
        Inquilino inquilinoDiez = mock(Inquilino.class);
        Inquilino inquilinoOnce = mock(Inquilino.class);
        Inquilino inquilinoDoce = mock(Inquilino.class);
        
        this.sis.añadirUsuario(propietario);
        this.sis.añadirUsuario(inquilinoUno);
        this.sis.añadirUsuario(inquilinoDos);
        this.sis.añadirUsuario(inquilinoTres);
        this.sis.añadirUsuario(inquilinoCuatro);
        this.sis.añadirUsuario(inquilinoCinco);
        this.sis.añadirUsuario(inquilinoSeis);
        this.sis.añadirUsuario(inquilinoSiete);
        this.sis.añadirUsuario(inquilinoOcho);
        this.sis.añadirUsuario(inquilinoNueve);
        this.sis.añadirUsuario(inquilinoDiez);
        this.sis.añadirUsuario(inquilinoOnce);
        this.sis.añadirUsuario(inquilinoDoce);
        
        when(propietario.esInquilino()).thenReturn(false);
        when(inquilinoUno.esInquilino()).thenReturn(true);
        when(inquilinoDos.esInquilino()).thenReturn(true);
        when(inquilinoTres.esInquilino()).thenReturn(true);
        when(inquilinoCuatro.esInquilino()).thenReturn(true);
        when(inquilinoCinco.esInquilino()).thenReturn(true);
        when(inquilinoSeis.esInquilino()).thenReturn(true);
        when(inquilinoSiete.esInquilino()).thenReturn(true);
        when(inquilinoOcho.esInquilino()).thenReturn(true);
        when(inquilinoNueve.esInquilino()).thenReturn(true);
        when(inquilinoDiez.esInquilino()).thenReturn(true);
        when(inquilinoOnce.esInquilino()).thenReturn(true);
        when(inquilinoDoce.esInquilino()).thenReturn(true);
        when(inquilinoUno.getCantidadDeAlquileres()).thenReturn(100);
        when(inquilinoDos.getCantidadDeAlquileres()).thenReturn(110);
        when(inquilinoTres.getCantidadDeAlquileres()).thenReturn(120);
        when(inquilinoCuatro.getCantidadDeAlquileres()).thenReturn(130);
        when(inquilinoCinco.getCantidadDeAlquileres()).thenReturn(140);
        when(inquilinoSeis.getCantidadDeAlquileres()).thenReturn(150);
        when(inquilinoSiete.getCantidadDeAlquileres()).thenReturn(160);
        when(inquilinoOcho.getCantidadDeAlquileres()).thenReturn(170);
        when(inquilinoNueve.getCantidadDeAlquileres()).thenReturn(180);
        when(inquilinoDiez.getCantidadDeAlquileres()).thenReturn(190);
        when(inquilinoOnce.getCantidadDeAlquileres()).thenReturn(200);
        when(inquilinoDoce.getCantidadDeAlquileres()).thenReturn(210);
        List<Usuario> inquilinos = this.sis.topTenDeInquilinos();
        
        assertEquals(10, inquilinos.size());
        assertTrue(inquilinos.contains(inquilinoDoce));
        assertTrue(inquilinos.contains(inquilinoOnce));
        assertTrue(inquilinos.contains(inquilinoDiez));
        assertTrue(inquilinos.contains(inquilinoNueve));
        assertTrue(inquilinos.contains(inquilinoOcho));
        assertTrue(inquilinos.contains(inquilinoSiete));
        assertTrue(inquilinos.contains(inquilinoSeis));
        assertTrue(inquilinos.contains(inquilinoCinco));
        assertTrue(inquilinos.contains(inquilinoCuatro));
        assertTrue(inquilinos.contains(inquilinoTres));
        assertFalse(inquilinos.contains(inquilinoDos));
        assertFalse(inquilinos.contains(inquilinoUno));
        assertFalse(inquilinos.contains(propietario));
    }

	/*
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
	}*/
/*	
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
	
	@Test
	void testSeVerificaQueUnInmuebleEsteDisponibleEnUnPeriodo() {	
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024,11, 3));
		when(reserva.getInmueble()).thenReturn(casa);
		when(reserva.getFechaFin()).thenReturn(LocalDate.of(2024,11, 10));
		
		when(reserva1.getFechaInicio()).thenReturn(LocalDate.of(2024,11, 5));
		when(reserva1.getInmueble()).thenReturn(hotel);
		when(reserva1.getFechaFin()).thenReturn(LocalDate.of(2024,11, 15));
		
		when(reserva2.getFechaInicio()).thenReturn(LocalDate.of(2024,12, 5));
		when(reserva2.getInmueble()).thenReturn(hotel);
		when(reserva2.getFechaFin()).thenReturn(LocalDate.of(2024,12, 30));
		
		sis.registrar(reserva);
		sis.registrar(reserva1);
		sis.registrar(reserva2);
		
		assertTrue(sis.estaDisponible(casa, LocalDate.of(2024, 11, 5), LocalDate.of(2024, 11, 8)));
	}
	
	@Test
	void seObtieneLasReservasParaUnPeriodoDado() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva);
		reservas.add(reserva1);
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024,11, 3));
		when(reserva.getFechaFin()).thenReturn(LocalDate.of(2024,11, 10));

		when(reserva1.getFechaInicio()).thenReturn(LocalDate.of(2024,11, 5));
		when(reserva1.getFechaFin()).thenReturn(LocalDate.of(2024,11, 15));
		
		when(reserva2.getFechaInicio()).thenReturn(LocalDate.of(2024,12, 5));
		when(reserva2.getFechaFin()).thenReturn(LocalDate.of(2024,12, 15));
		
		sis.registrar(reserva);
		sis.registrar(reserva1);
		sis.registrar(reserva2);
		
		assertEquals(reservas, sis.getReservasParaPeriodo(LocalDate.of(2024, 11, 1), LocalDate.of(2024,11,30)));
	}
	*/
}
