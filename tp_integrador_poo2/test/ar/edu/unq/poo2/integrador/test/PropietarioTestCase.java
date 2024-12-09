package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Calificable;
import ar.edu.unq.poo2.integrador.Calificacion;
import ar.edu.unq.poo2.integrador.Categoria;
import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.Sistema;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

import static org.mockito.Mockito.*;

class PropietarioTestCase {
	
	private String nombre;
	private String email;
	private int tel;
	private Sistema sistema;
	private LocalDate fechaDeIngreso;
	private Propietario propietario;
	private Calificacion calificacion;
	private Categoria buenServicio;
	
	@BeforeEach
	void setUp() throws Exception {
		this.nombre = "Juan Rodrigez";
		this.email = "email@mail.com";
		this.tel = 1554987566;
		this.sistema = mock(Sistema.class);
		this.fechaDeIngreso = LocalDate.of(2022, 8, 24);
		this.calificacion = mock(Calificacion.class);
		this.buenServicio = mock(Categoria.class);
		this.propietario = new Propietario(nombre, email, tel, fechaDeIngreso, sistema);
	}

	@Test
	void test_creacionDePropietarioConSusDatos() {

		Propietario juan = new Propietario(nombre, email, tel, fechaDeIngreso, sistema);
		
		assertEquals(juan.getNombre() , nombre);
		assertEquals(juan.getEmail() , email);
		assertEquals(juan.getTelefono() , tel);
		assertEquals(juan.getFechaDeIngreso() , fechaDeIngreso);
		
	}   
	
	
	@Test
	void test_propietarioSabeHaceCuantoQueEsUsuario() {
		//setup
		Propietario propietario = new Propietario(nombre, email, tel, fechaDeIngreso, sistema);
		propietario.setFechaDeIngreso(LocalDate.of(2022, 11, 11));
		
		//exercise
		int anhosQueLlevaRegistrado = propietario.getAntiguedadEnSistema(); 
		
		//verify
		assertEquals(LocalDate.of(2022, 11, 11), propietario.getFechaDeIngreso());
		assertEquals(2, anhosQueLlevaRegistrado);
		
		
	}
	
	@Test
	void test_propietarioDaDeAltaInmueble() {
		//setup
		Inmueble inmueble = mock(Inmueble.class);
	
		
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
		Inmueble casa = mock(Inmueble.class);
		Inmueble rancho = mock(Inmueble.class);
		Inmueble palacio = mock(Inmueble.class);
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
		Inmueble casa = mock(Inmueble.class);
		Inmueble rancho = mock(Inmueble.class);
		Inmueble palacio = mock(Inmueble.class);
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
