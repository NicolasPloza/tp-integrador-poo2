package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

class TipoInmuebleTestCase {

	@Test
	void testInicializacion() {
		TipoInmueble casa = new TipoInmueble("Casa", "3 ambientes");
		
		assertEquals("Casa", casa.getNombre());
		assertEquals("3 ambiente", casa.getDescripcion());
	}

}
