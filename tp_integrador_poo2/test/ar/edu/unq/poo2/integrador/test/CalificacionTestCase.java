package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Calificacion;
import ar.edu.unq.poo2.integrador.Categoria;

class CalificacionTestCase {
	
	private Calificacion calificacion;
	private Categoria categoria;

	@BeforeEach
	void setUp() {
		this.categoria = mock(Categoria.class);
		this.calificacion = new Calificacion(this.categoria, 10);
	}

	@Test
	void testSeObtieneLaCategoriaDeUnaCalificacion() {
		assertEquals(this.categoria, this.calificacion.getCategoria());
	}
	
	@Test
	void testSeObtieneElPuntajeDeUnaCalificacion() {
		assertEquals(10, this.calificacion.getPuntaje());
	}

}
