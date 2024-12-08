package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroPrecioMaximo;

class FiltroPrecioMaximoTestCase {
	
	private FiltroPrecioMaximo filtro;
	private Inmueble inmueble;
	private double precio;

	@BeforeEach
	void setUp() {
		this.inmueble = mock(Inmueble.class);
		this.precio = 3000;
		this.filtro = new FiltroPrecioMaximo(this.precio);
	}

	@Test
	void testSeVerificaQueUnInmuebleCumplaLaCondicionPorPrecioMaximo() {
		when(this.inmueble.precioDefaultMenorOIgualA(this.precio)).thenReturn(true);
		
		assertTrue(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.inmueble).precioDefaultMenorOIgualA(this.precio);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoCumplaLaCondicionPorPrecioMaximo() {
		when(this.inmueble.precioDefaultMenorOIgualA(this.precio)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.inmueble).precioDefaultMenorOIgualA(this.precio);
	}

}
