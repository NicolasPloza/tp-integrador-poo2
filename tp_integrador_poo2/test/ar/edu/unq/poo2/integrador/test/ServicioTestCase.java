package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Servicio;

class ServicioTestCase {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testInicializacion() {
		Servicio wifi = new Servicio("WIFI", "Tiene velocidad de subida de: 100mb/. De bajada: 50mb/s");
		
		assertEquals("WIFI", wifi.getNombre());
		assertEquals("Tiene velocidad de subida de: 100mb/. De bajada: 50mb/s",
				     wifi.getDescripcion());
	}

}
