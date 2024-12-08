package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroOpcional;
import ar.edu.unq.poo2.integrador.moduloSearch.Search;

class SearchTestCase {
	
	private Search filtro;
	private String ciudad;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FiltroOpcional filtroOpcional;
	private Inmueble departamento;
	private Inmueble casa;
	private Inmueble duplex;
	private List<Inmueble> inmuebles;

	@BeforeEach
	void setUp() {
		this.ciudad = "Mar del Plata";
		this.fechaEntrada = LocalDate.of(2030, 3, 20);
		this.fechaSalida = LocalDate.of(2030, 4, 10);
		this.filtroOpcional = mock(FiltroOpcional.class);
		this.departamento = mock(Inmueble.class);
		this.casa = mock(Inmueble.class);
		this.duplex = mock(Inmueble.class);
		this.filtro = new Search(this.ciudad, this.fechaEntrada, this.fechaSalida, this.filtroOpcional);
		this.inmuebles = Arrays.asList(this.departamento, this.casa, this.duplex);
	}

	@Test
	void testSeVerificaQueUnInmuebleCumplaLasCondicionesDeLosFiltros() {
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		when(this.filtroOpcional.cumpleCondicion(this.departamento)).thenReturn(true);
		
		assertTrue(this.filtro.cumpleCondicion(this.departamento));
		verify(this.departamento).esDeCiudad(this.ciudad);
		verify(this.departamento).estaDisponibleEn(this.fechaEntrada, this.fechaSalida);
		verify(this.filtroOpcional).cumpleCondicion(this.departamento);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleCumplaDosDeLasCondicionesDeLosFiltros() {
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		when(this.filtroOpcional.cumpleCondicion(this.departamento)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.departamento));
		verify(this.departamento).esDeCiudad(this.ciudad);
		verify(this.departamento).estaDisponibleEn(this.fechaEntrada, this.fechaSalida);
		verify(this.filtroOpcional).cumpleCondicion(this.departamento);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleCumplaUnoDeLasCondicionesDeLosFiltros() {
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.departamento));
		verify(this.departamento).esDeCiudad(this.ciudad);
		verify(this.departamento).estaDisponibleEn(this.fechaEntrada, this.fechaSalida);
		verify(this.filtroOpcional, never()).cumpleCondicion(this.departamento);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoCumplaNingunaDeLasCondicionesDeLosFiltros() {
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.departamento));
		verify(this.departamento).esDeCiudad(this.ciudad);
		verify(this.departamento, never()).estaDisponibleEn(this.fechaEntrada, this.fechaSalida);
		verify(this.filtroOpcional, never()).cumpleCondicion(this.departamento);
	}
	
	@Test
	void testSeObtienenLosInmueblesQueCumplanLaCondicionEnUnBuscador() {
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(false);
		when(this.casa.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.casa.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		when(this.filtroOpcional.cumpleCondicion(this.casa)).thenReturn(true);
		when(this.duplex.esDeCiudad(this.ciudad)).thenReturn(false);
		List<Inmueble> inmueblesFiltrados = this.filtro.filtrar(this.inmuebles);
		
		assertEquals(1, inmueblesFiltrados.size());
		assertTrue(inmueblesFiltrados.contains(this.casa));
		assertFalse(inmueblesFiltrados.contains(this.departamento));
		assertFalse(inmueblesFiltrados.contains(this.duplex));
	}

}
