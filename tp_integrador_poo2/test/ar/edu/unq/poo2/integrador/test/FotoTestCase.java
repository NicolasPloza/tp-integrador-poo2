package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Foto;

class FotoTestCase {
	@Test
	void testInicializacion() {
		Foto living = new Foto("Living", "Amplio espacio, con televisor 4K");
		assertEquals("Living", living.getNombre());
		assertEquals("Amplio espacio, con televisor 4K", living.getDescripcion());
	}

}
