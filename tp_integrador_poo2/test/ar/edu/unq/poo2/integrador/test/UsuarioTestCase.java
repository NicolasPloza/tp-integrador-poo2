package ar.edu.unq.poo2.integrador.test;

import ar.edu.unq.poo2.integrador.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class UsuarioTestCase {
	private Usuario usuarioDePrueba;
	private Categoria servicio;
	private Categoria buenTrato ;
	private Categoria puntualidad ;
	
	@BeforeEach
	void setUp() throws Exception {
		usuarioDePrueba = new Usuario("nombre apellido", "email@mail.com", 1554987566);
		puntualidad = crearMockCategoria("Puntualidad", "descripcion");
		buenTrato = crearMockCategoria("Buen trato", "descripcion");
		servicio = crearMockCategoria("Servicio", "descripcion");
	}

	@Test
	void test_creacionDeUsuarioConSusDatos() {
		String nombre = "Juan Rodriguez";
		String email = "useremail@mail.com";
		int tel = 1522334455;
		
		Usuario user = new Usuario(nombre, email, tel);
		
		assertEquals(nombre, user.getNombre());
		assertEquals(email, user.getEmail());
		assertEquals(tel, user.getTelefono());
	}
	
	@Test 
	void test_usuarioPuedeSetearSusDatos() {
		Usuario user = new Usuario();
		
		user.setNombre("usuario");
		user.setEmail("mail");
		user.setTelefono(112244556);
		
		assertEquals("usuario", user.getNombre());
		assertEquals("mail", user.getEmail());
		assertEquals(112244556, user.getTelefono());
	}
	
	@Test
	void test_UsuarioRecibeUnaCalificacion() {
		//setup
		Calificacion servicio = mock(Calificacion.class);
		
		//exercise	
		usuarioDePrueba.agregarCalificacion(servicio);
		List<Calificacion> calificaciones = usuarioDePrueba.getCalificaciones();
		
		//verify
		assertFalse(calificaciones.isEmpty());
		assertTrue(calificaciones.contains(servicio));
	}
	
	@Test
	void test_UsuarioSabeElPromedioDeSusPuntajes() {
		//setup
		Calificacion serv = this.crearMockCalificacion(servicio, 4);
		Calificacion trato = this.crearMockCalificacion(buenTrato, 2);
		Calificacion punt = this.crearMockCalificacion(puntualidad, 3);
		
		usuarioDePrueba.agregarCalificacion(punt);
		usuarioDePrueba.agregarCalificacion(serv);
		usuarioDePrueba.agregarCalificacion(trato);
		
		//exercise	
		double promedio = usuarioDePrueba.getPromedioTotalDePuntajes();
				
		//verify
		assertEquals(promedio , 3.00);
		
	}
	
	@Test
	void test_UsuarioSabeElPromedioDeSusPuntajes_PorCategoria() {
		//setup
		Calificacion serv_calificacion_1 = this.crearMockCalificacion(servicio, 5);
		Calificacion serv_calificacion_2 = this.crearMockCalificacion(servicio, 3);
		Calificacion trato_calificacion_1 = this.crearMockCalificacion(buenTrato, 2);
		Calificacion trato_calificacion_2 = this.crearMockCalificacion(buenTrato, 1);
		Calificacion trato_calificacion_3 = this.crearMockCalificacion(buenTrato, 4);
		Calificacion puntualidad_calificacion = this.crearMockCalificacion(puntualidad, 3);
		
		usuarioDePrueba.agregarCalificacion(serv_calificacion_1);
		usuarioDePrueba.agregarCalificacion(serv_calificacion_2);
		usuarioDePrueba.agregarCalificacion(trato_calificacion_1);
		usuarioDePrueba.agregarCalificacion(trato_calificacion_2);
		usuarioDePrueba.agregarCalificacion(trato_calificacion_3);
		usuarioDePrueba.agregarCalificacion(puntualidad_calificacion);
		
		//exercise	
		double promedio_serv = usuarioDePrueba.getPromedio(servicio.getNombre());
		double promedio_trato = usuarioDePrueba.getPromedio(buenTrato.getNombre());
		double promedio_puntualidad = usuarioDePrueba.getPromedio(puntualidad.getNombre());
			
		
		//verify
		assertEquals(promedio_serv , 4.00);
		assertEquals(promedio_trato , 2.33);
		assertEquals(promedio_puntualidad , 3.0);
		
	}
	
	
	
	private Calificacion crearMockCalificacion(Categoria categoria, int puntaje){
		
		Calificacion mockCalificacion = mock(Calificacion.class);
		
		when(mockCalificacion.getPuntaje()).thenReturn(puntaje);
		when(mockCalificacion.getCategoria()).thenReturn(categoria);
		
		return mockCalificacion;
	}
	
	private Categoria crearMockCategoria(String nombre, String descripcion){
		
		Calificacion mockCategoria = mock(Categoria.class);
		
		when(mockCategoria.getNombre()).thenReturn(nombre);
		when(mockCategoria.getDescripcion()).thenReturn(descripcion);
		
		return mockCategoria;
	}
	
	/* casos borde: 
	   - creacion de usuario q no esta registrado
	   - puntajes invalidos (que no sean del 1 al 5)
	   - tiene que conocer a sistema?
	*/
	
}