package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroPrecioMinimo;

class FiltroPrecioMinimoTestCase {
	
	private FiltroPrecioMinimo filtro;
	private Inmueble inmueble;
	private double precio;

	@BeforeEach
	void setUp() {
		this.inmueble = mock(Inmueble.class);
		this.precio = 7000;
		this.filtro = new FiltroPrecioMinimo(this.precio);
	}

	@Test
	void testSeVerificaQueUnInmuebleCumplaLaCondicionDePrecioMinimo() {
		when(this.inmueble.precioDefaultMayorOIgualA(this.precio)).thenReturn(true);
		
		assertTrue(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.inmueble).precioDefaultMayorOIgualA(this.precio);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoCumplaLaCondicionDePrecioMinimo() {
		when(this.inmueble.precioDefaultMayorOIgualA(this.precio)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.inmueble).precioDefaultMayorOIgualA(this.precio);
	}

}
