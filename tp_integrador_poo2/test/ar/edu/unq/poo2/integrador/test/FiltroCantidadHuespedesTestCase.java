package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroCantidadHuespedes;

class FiltroCantidadHuespedesTestCase {
	
	private FiltroCantidadHuespedes filtro;
	private Inmueble inmueble;
	private int cantidadDeHuespedes;

	@BeforeEach
	void setUp() {
		this.inmueble = mock(Inmueble.class);
		this.cantidadDeHuespedes = 4;
		this.filtro = new FiltroCantidadHuespedes(this.cantidadDeHuespedes);
	}

	@Test
	void testSeVerificaQueUnInmuebleCumplaLaCondicionPorCantidadDeHuespedes() {
		when(this.inmueble.tieneCapacidadDe(this.cantidadDeHuespedes)).thenReturn(true);
		
		assertTrue(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.inmueble).tieneCapacidadDe(this.cantidadDeHuespedes);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoCumplaLaCondicionPorCantidadDeHuespedes() {
		when(this.inmueble.tieneCapacidadDe(this.cantidadDeHuespedes)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.inmueble).tieneCapacidadDe(this.cantidadDeHuespedes);
	}

}
