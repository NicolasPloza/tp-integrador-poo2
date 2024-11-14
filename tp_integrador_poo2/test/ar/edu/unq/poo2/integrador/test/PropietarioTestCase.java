package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.Sistema;
import static org.mockito.Mockito.*;

class PropietarioTestCase {
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void test_creacionDePropietarioConSusDatos() {
		String nombre = "Juan Rodrigez";
		String email = "email@mail.com";
		int tel = 1554987566;
		Sistema sistema = mock(Sistema.class);
		LocalDate fechaDeIngreso = LocalDate.of(2022, 8, 24);
		
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
	
	
	
}    
