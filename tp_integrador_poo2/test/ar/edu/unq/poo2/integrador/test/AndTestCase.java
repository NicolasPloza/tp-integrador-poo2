package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.moduloSearch.And;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroOpcional;

class AndTestCase {
	
	private And filtro;
	private FiltroOpcional primerFiltroOpcional;
	private FiltroOpcional segundoFiltroOpcional;
	private Inmueble inmueble;

	@BeforeEach
	void setUp() {
		this.primerFiltroOpcional = mock(FiltroOpcional.class);
		this.segundoFiltroOpcional = mock(FiltroOpcional.class);
		this.inmueble = mock(Inmueble.class);
		this.filtro = new And(this.primerFiltroOpcional, this.segundoFiltroOpcional);
	}

	@Test
	void testSeVerificaQueUnInmuebleCumplaAmbasCondicionesDeLosFiltros() {
		when(this.primerFiltroOpcional.cumpleCondicion(this.inmueble)).thenReturn(true);
		when(this.segundoFiltroOpcional.cumpleCondicion(this.inmueble)).thenReturn(true);
		
		assertTrue(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.primerFiltroOpcional).cumpleCondicion(this.inmueble);
		verify(this.segundoFiltroOpcional).cumpleCondicion(this.inmueble);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoCumplaLaPrimeraCondicionDeLosFiltros() {
		when(this.primerFiltroOpcional.cumpleCondicion(this.inmueble)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.primerFiltroOpcional).cumpleCondicion(this.inmueble);
		verify(this.segundoFiltroOpcional, never()).cumpleCondicion(this.inmueble);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoCumplaLaSegundaCondicionDeLosFiltros() {
		when(this.primerFiltroOpcional.cumpleCondicion(this.inmueble)).thenReturn(true);
		when(this.segundoFiltroOpcional.cumpleCondicion(this.inmueble)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.inmueble));
		verify(this.primerFiltroOpcional).cumpleCondicion(this.inmueble);
		verify(this.segundoFiltroOpcional).cumpleCondicion(this.inmueble);
	}

}
