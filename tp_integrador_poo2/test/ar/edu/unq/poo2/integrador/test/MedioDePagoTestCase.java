package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.MedioDePago;

class MedioDePagoTestCase {

	@Test
	void testInicializacion() {
		MedioDePago debito = new MedioDePago("Debito", "CBU: 12321xxxxx");
		
		assertEquals("Debito", debito.getNombre());
		assertEquals("CBU: 12321xxxxx", debito.getDescripcion());
	}
}
