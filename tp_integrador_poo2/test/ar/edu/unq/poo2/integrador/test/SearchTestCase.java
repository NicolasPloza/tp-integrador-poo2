package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
		this.filtro = new Search(this.ciudad, this.fechaEntrada, this.fechaSalida);
		this.inmuebles = Arrays.asList(this.departamento, this.casa, this.duplex);
	}

	@Test
	void testSeVerificaQueUnInmuebleCumplaLasCondicionesDeLosFiltros() {
		this.filtro.setFiltro(this.filtroOpcional);
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
		this.filtro.setFiltro(this.filtroOpcional);
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
		this.filtro.setFiltro(this.filtroOpcional);
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.departamento));
		verify(this.departamento).esDeCiudad(this.ciudad);
		verify(this.departamento).estaDisponibleEn(this.fechaEntrada, this.fechaSalida);
		verify(this.filtroOpcional, never()).cumpleCondicion(this.departamento);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleNoCumplaNingunaDeLasCondicionesDeLosFiltros() {
		this.filtro.setFiltro(this.filtroOpcional);
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(false);
		
		assertFalse(this.filtro.cumpleCondicion(this.departamento));
		verify(this.departamento).esDeCiudad(this.ciudad);
		verify(this.departamento, never()).estaDisponibleEn(this.fechaEntrada, this.fechaSalida);
		verify(this.filtroOpcional, never()).cumpleCondicion(this.departamento);
	}
	
	@Test
	void testSeVerificaQueUnInmuebleCumplaLasCondicionesDeLosFiltrosObligatorios() {
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		
		assertTrue(this.filtro.cumpleCondicion(this.departamento));
		verify(this.departamento).esDeCiudad(this.ciudad);
		verify(this.departamento).estaDisponibleEn(this.fechaEntrada, this.fechaSalida);
	}
	
	@Test
	void testSeObtienenLosInmueblesQueCumplanLaCondicionEnUnBuscador() {
		this.filtro.setFiltro(this.filtroOpcional);
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
	
	@Test
	void testSeVerificaQueLosInmueblesNoCumplanLaCondicionEnUnBuscador() {
		this.filtro.setFiltro(this.filtroOpcional);
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(false);
		when(this.casa.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.casa.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		when(this.filtroOpcional.cumpleCondicion(this.casa)).thenReturn(false);
		when(this.duplex.esDeCiudad(this.ciudad)).thenReturn(false);
		List<Inmueble> inmueblesFiltrados = this.filtro.filtrar(this.inmuebles);
		
		assertEquals(0, inmueblesFiltrados.size());
		assertFalse(inmueblesFiltrados.contains(this.casa));
		assertFalse(inmueblesFiltrados.contains(this.departamento));
		assertFalse(inmueblesFiltrados.contains(this.duplex));
	}
	
	@Test
	void testSeVerificaQueLosInmueblesCumplanLaCondicionEnUnBuscador() {
		this.filtro.setFiltro(this.filtroOpcional);
		when(this.departamento.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.departamento.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		when(this.filtroOpcional.cumpleCondicion(this.departamento)).thenReturn(true);
		when(this.casa.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.casa.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		when(this.filtroOpcional.cumpleCondicion(this.casa)).thenReturn(true);
		when(this.duplex.esDeCiudad(this.ciudad)).thenReturn(true);
		when(this.duplex.estaDisponibleEn(this.fechaEntrada, this.fechaSalida)).thenReturn(true);
		when(this.filtroOpcional.cumpleCondicion(this.duplex)).thenReturn(true);
		List<Inmueble> inmueblesFiltrados = this.filtro.filtrar(this.inmuebles);
		
		assertEquals(3, inmueblesFiltrados.size());
		assertTrue(inmueblesFiltrados.contains(this.casa));
		assertTrue(inmueblesFiltrados.contains(this.departamento));
		assertTrue(inmueblesFiltrados.contains(this.duplex));
	}
	
	@Test
	void testSeVerificaQueSeObtengaUnListadoVacioPorNoTenerInmueblesParaFiltrarEnUnBuscador() {
		List<Inmueble> inmueblesVacios = new ArrayList<Inmueble>();
		
		assertEquals(0, this.filtro.filtrar(inmueblesVacios).size());
		assertTrue(this.filtro.filtrar(inmueblesVacios).isEmpty());
	}

}
