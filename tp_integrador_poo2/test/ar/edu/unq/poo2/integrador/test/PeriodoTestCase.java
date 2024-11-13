package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.inmueble.Periodo;

class PeriodoTestCase {
	LocalDate fechaInicio;
	LocalDate fechaFin;
	Periodo vacaciones;
	
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicio = LocalDate.of(2025, 1, 1);
		fechaFin = LocalDate.of(2025, 1, 29);
		vacaciones = new Periodo("vacaciones", fechaInicio, fechaFin, 
                500.0);
	}

	@Test
	void testInicializacion() {	
		assertEquals("vacaciones", vacaciones.getNombre());
		assertEquals(fechaInicio, vacaciones.getFechaInicio());
		assertEquals(fechaFin, vacaciones.getFechaFin());
		assertEquals(500.0, vacaciones.getPrecioPorDia());
	}
	
	@Test
	void testFechaSeEncuetraDentroDelPeriodo() {
		LocalDate fechaAVerificar = LocalDate.of(2025, 1, 15);
		
		assertTrue(vacaciones.esFechaDePeriodo(fechaAVerificar));
	}
	@Test
	void testFechaNoSeEncuetraDentroDelPeriodo() {
		LocalDate fechaAVerificar = LocalDate.of(2025, 3, 15);
		
		assertFalse(vacaciones.esFechaDePeriodo(fechaAVerificar));
	}
	
}
