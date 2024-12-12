package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	private Servicio luz;
	private TipoInmueble duplex;
	private List<Servicio> servicios;
	private Calificable propietario;
	private Categoria buenServicio;
	
	@BeforeEach
	void setUp() throws Exception {
		this.sis = new Sistema();
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
		when(inmueble.getServicios()).thenReturn(Arrays.asList(this.luz));
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
		when(inmueble.getServicios()).thenReturn(Arrays.asList(agua));
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
	

	
	@Test 
	void test_seVerificaQueElSistemaConoceLosInmueblesLibres() {
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		Inmueble inmueble3 = mock(Inmueble.class);
		Inmueble inmueble4 = mock(Inmueble.class);
		
		when(inmueble1.estaAlquilado()).thenReturn(true);
		when(inmueble1.getServicios()).thenReturn(servicios);
		when(inmueble1.getTipoDeInmueble()).thenReturn(duplex);
		
		when(inmueble2.estaAlquilado()).thenReturn(true);
		when(inmueble2.getServicios()).thenReturn(servicios);
		when(inmueble2.getTipoDeInmueble()).thenReturn(duplex);
		
		when(inmueble3.estaAlquilado()).thenReturn(false);
		when(inmueble3.getServicios()).thenReturn(servicios);
		when(inmueble3.getTipoDeInmueble()).thenReturn(duplex);
		
		when(inmueble4.estaAlquilado()).thenReturn(false);
		when(inmueble4.getServicios()).thenReturn(servicios);
		when(inmueble4.getTipoDeInmueble()).thenReturn(duplex);
		
		sis.agregarServicio(luz);
		sis.agregarTipoDeInmueble(duplex);
		
		sis.registrarInmueble(inmueble1);
		sis.registrarInmueble(inmueble2);
		sis.registrarInmueble(inmueble3);
		sis.registrarInmueble(inmueble4);
		
		//exercise
		List<Inmueble> inmueblesLibres = sis.getInmueblesLibres();
		
		//verify
		assertFalse(inmueblesLibres.contains(inmueble1));
		assertFalse(inmueblesLibres.contains(inmueble2));
		assertTrue(inmueblesLibres.contains(inmueble3));
		assertTrue(inmueblesLibres.contains(inmueble4));
		
	}
	
	@Test
	void test_seVerificaQueElSistemaConoceLaTasaDeOcupacion(){
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		Inmueble inmueble3 = mock(Inmueble.class);
		Inmueble inmueble4 = mock(Inmueble.class);
		
		when(inmueble1.estaAlquilado()).thenReturn(true);
		when(inmueble1.getServicios()).thenReturn(servicios);
		when(inmueble1.getTipoDeInmueble()).thenReturn(duplex);
		
		when(inmueble2.estaAlquilado()).thenReturn(true);
		when(inmueble2.getServicios()).thenReturn(servicios);
		when(inmueble2.getTipoDeInmueble()).thenReturn(duplex);
		
		when(inmueble3.estaAlquilado()).thenReturn(false);
		when(inmueble3.getServicios()).thenReturn(servicios);
		when(inmueble3.getTipoDeInmueble()).thenReturn(duplex);
		
		when(inmueble4.estaAlquilado()).thenReturn(false);
		when(inmueble4.getServicios()).thenReturn(servicios);
		when(inmueble4.getTipoDeInmueble()).thenReturn(duplex);
		
		sis.agregarServicio(luz);
		sis.agregarTipoDeInmueble(duplex);
		
		sis.registrarInmueble(inmueble1);
		sis.registrarInmueble(inmueble2);
		sis.registrarInmueble(inmueble3);
		sis.registrarInmueble(inmueble4);
		
		//exercise
		double tasaDeOcupacion =  sis.getTasaDeOcupacion();
		
		//verify
		assertEquals( 0.5 , tasaDeOcupacion);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
