package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Calificacion;
import ar.edu.unq.poo2.integrador.Categoria;

class CalificacionTestCase {
	
	private Calificacion calificacionMayor;
	private Calificacion calificacionMenor;
	private Categoria categoria;

	@BeforeEach
	void setUp() {
		this.categoria = mock(Categoria.class);
		this.calificacionMayor = new Calificacion(this.categoria, 10);
		this.calificacionMenor = new Calificacion(this.categoria, 0);
	}

	@Test
	void testSeObtieneLaCategoriaDeUnaCalificacion() {
		assertEquals(this.categoria, this.calificacionMayor.getCategoria());
	}
	
	@Test
	void testSeVerificaQueElMaximoPuntajePosibleSeaCinco() {
		assertEquals(5, this.calificacionMayor.getPuntaje());
	}
	
	@Test
	void testSeVerificaQueElMinimoPuntajePosibleSeaUno() {
		assertEquals(1, this.calificacionMenor.getPuntaje());
	}

}
