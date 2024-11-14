package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.Sistema;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

import static org.mockito.Mockito.*;

class PropietarioTestCase {
	String nombre;
	String email;
	int tel;
	Sistema sistema;
	LocalDate fechaDeIngreso ;
	Propietario propietario;
	
	@BeforeEach
	void setUp() throws Exception {
		nombre = "Juan Rodrigez";
		email = "email@mail.com";
		tel = 1554987566;
		sistema = mock(Sistema.class);
		fechaDeIngreso = LocalDate.of(2022, 8, 24);
		propietario = new Propietario(nombre, email, tel, fechaDeIngreso, sistema);
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
		Propietario propietario = new Propietario();
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
	
	
	
}    
