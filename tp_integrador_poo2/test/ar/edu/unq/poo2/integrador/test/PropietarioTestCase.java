package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Calificable;
import ar.edu.unq.poo2.integrador.Calificacion;
import ar.edu.unq.poo2.integrador.Categoria;
import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.Sistema;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.Servicio;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

import static org.mockito.Mockito.*;

class PropietarioTestCase {
	
	private String nombre;
	private String email;
	private int tel;
	private Sistema sistema;
	private Propietario propietario;
	private Calificacion calificacion;
	private Categoria buenServicio;
	
	@BeforeEach
	void setUp() throws Exception {
		this.nombre = "Juan Rodrigez";
		this.email = "email@mail.com";
		this.tel = 1554987566;
		this.sistema = mock(Sistema.class);
		this.calificacion = mock(Calificacion.class);
		this.buenServicio = mock(Categoria.class);
		this.propietario = new Propietario(nombre, email, tel, sistema);
	}

	@Test
	void test_creacionDePropietarioConSusDatos() {

		Propietario juan = new Propietario(nombre, email, tel, sistema);
		
		assertEquals(juan.getNombre() , nombre);
		assertEquals(juan.getEmail() , email);
		assertEquals(juan.getTelefono() , tel);
		assertEquals(juan.getFechaDeIngreso() , LocalDate.now());
		
	}   
	
	
	@Test
	void test_propietarioSabeHaceCuantoQueEsUsuario() {
		//setup
		Propietario propietario = new Propietario(nombre, email, tel, sistema);
		
		//exercise
		int añosQueLlevaRegistrado = propietario.getAntiguedadEnSistema(); 
		
		//verify
		assertEquals(LocalDate.now(), propietario.getFechaDeIngreso());
		assertEquals(0, añosQueLlevaRegistrado);
		
		
	}
	
	@Test
	void test_propietarioDaDeAltaInmueble() {
		//setup
		TipoInmueble tipoDeInmueble = mock(TipoInmueble.class);
		List<Servicio> servicios = Arrays.asList(mock(Servicio.class));
		Inmueble inmueble = mock(Inmueble.class);
		when(inmueble.getTipoDeInmueble()).thenReturn(tipoDeInmueble);
		when(inmueble.getServicios()).thenReturn(servicios);
		when(this.sistema.acepta(tipoDeInmueble, servicios)).thenReturn(true);
		
		//exercise
		propietario.realizarAlta(inmueble);
		
		//verify
		assertFalse( propietario.getInmuebles().isEmpty());
		verify(sistema).registrarInmueble(inmueble);
		
	}
	
	@Test
	void testSeAgregaUnaCalificacionParaUnPropietario() {
		when(this.calificacion.getCategoria()).thenReturn(this.buenServicio);
		when(this.sistema.tieneCategoriaPara(Calificable.PROPIETARIO, this.buenServicio)).thenReturn(true);
		this.propietario.agregarCalificacion(this.calificacion);
		
		assertEquals(1, this.propietario.getCalificaciones().size());
		assertTrue(this.propietario.getCalificaciones().contains(this.calificacion));
	}
	
	@Test
	void testSeAgregaUnaCalificacionDeUnCategoriaEquivocadaParaUnPropietario() {
		when(this.calificacion.getCategoria()).thenReturn(this.buenServicio);
		when(this.sistema.tieneCategoriaPara(Calificable.PROPIETARIO, this.buenServicio)).thenReturn(false);
		this.propietario.agregarCalificacion(this.calificacion);
		
		assertEquals(0, this.propietario.getCalificaciones().size());
		assertFalse(this.propietario.getCalificaciones().contains(this.calificacion));
	}
	
	@Test
	void test_seVerificaQueUnPropietarioSabeLaCantidadDeInmueblesDadosDeAltaQueTiene() {
		//setup
		TipoInmueble tipoDeInmueble = mock(TipoInmueble.class);
		List<Servicio> servicios = Arrays.asList(mock(Servicio.class));
		Inmueble casa = mock(Inmueble.class);
		Inmueble rancho = mock(Inmueble.class);
		Inmueble palacio = mock(Inmueble.class);
		when(casa.getTipoDeInmueble()).thenReturn(tipoDeInmueble);
		when(casa.getServicios()).thenReturn(servicios);
		when(rancho.getTipoDeInmueble()).thenReturn(tipoDeInmueble);
		when(rancho.getServicios()).thenReturn(servicios);
		when(palacio.getTipoDeInmueble()).thenReturn(tipoDeInmueble);
		when(palacio.getServicios()).thenReturn(servicios);
		when(this.sistema.acepta(tipoDeInmueble, servicios)).thenReturn(true);
		propietario.realizarAlta(casa);
		propietario.realizarAlta(rancho);
		propietario.realizarAlta(palacio);
		
		//exercise
		int cantidadDeInmuebles =  propietario.getCantidadDeInmuebles();
		
		//verify
		assertEquals(3 , cantidadDeInmuebles);
	}
	
	
	@Test
	void test_seVerificaQueUnPropietarioSabeLaCantidadDeVecesQueAlquiloUnInmueble_yCantidadTotal() {
		//setup
		TipoInmueble tipoDeInmueble = mock(TipoInmueble.class);
		List<Servicio> servicios = Arrays.asList(mock(Servicio.class));
		Inmueble casa = mock(Inmueble.class);
		Inmueble rancho = mock(Inmueble.class);
		Inmueble palacio = mock(Inmueble.class);
		when(casa.getTipoDeInmueble()).thenReturn(tipoDeInmueble);
		when(casa.getServicios()).thenReturn(servicios);
		when(rancho.getTipoDeInmueble()).thenReturn(tipoDeInmueble);
		when(rancho.getServicios()).thenReturn(servicios);
		when(palacio.getTipoDeInmueble()).thenReturn(tipoDeInmueble);
		when(palacio.getServicios()).thenReturn(servicios);
		when(this.sistema.acepta(tipoDeInmueble, servicios)).thenReturn(true);
		propietario.realizarAlta(casa);
		propietario.realizarAlta(rancho);
		propietario.realizarAlta(palacio);
		
		when(casa.cantidadDeAlquileres()).thenReturn(0);
		when(rancho.cantidadDeAlquileres()).thenReturn(3);
		when(palacio.cantidadDeAlquileres()).thenReturn(4);
		
		//exercise - verify

		assertEquals(7 , propietario.cantidadTotalDeAlquileres() );
	}
	
	
	
}    
