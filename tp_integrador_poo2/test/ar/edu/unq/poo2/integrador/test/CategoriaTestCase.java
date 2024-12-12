package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.Categoria;

class CategoriaTestCase {

	@Test
	void test() {
		Categoria buenTrato = new Categoria("Buen Trato", "Abarca cosas generales: como amabilidad, solucion de problemas, etc.");
		
		assertEquals("Buen Trato", buenTrato.getNombre());
		assertEquals("Abarca cosas generales: como amabilidad, solucion de problemas, etc.", buenTrato.getDescripcion());
	}
}
