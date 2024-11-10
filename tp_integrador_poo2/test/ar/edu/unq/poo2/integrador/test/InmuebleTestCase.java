package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.*;
import ar.edu.unq.poo2.integrador.Calificacion;
import ar.edu.unq.poo2.integrador.Propietario;
import ar.edu.unq.poo2.integrador.inmueble.Cancelacion;
import ar.edu.unq.poo2.integrador.inmueble.Foto;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.MedioDePago;
import ar.edu.unq.poo2.integrador.inmueble.Periodo;
import ar.edu.unq.poo2.integrador.inmueble.PoliticaDeCancelacion;
import ar.edu.unq.poo2.integrador.inmueble.Servicio;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

class InmuebleTestCase {
	TipoInmueble casa;
	List<Servicio> servicios;
	List<MedioDePago> mediosDePago;
	Propietario x;
	List<Calificacion> calificaciones;
	List<Foto> fotos;
	Inmueble alquiler1;
	PoliticaDeCancelacion cancelacion;
	List<Periodo> periodos;
	GestionadorDeNotificaciones gestionador;
	
	@BeforeEach
	void setUp() {
		casa = mock(TipoInmueble.class);
		servicios = Arrays.asList(mock(Servicio.class), mock(Servicio.class));
		mediosDePago = Arrays.asList(mock(MedioDePago.class), mock(MedioDePago.class));
		fotos = Arrays.asList(mock(Foto.class), mock(Foto.class));
		alquiler1 = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 3000.0, casa, mediosDePago, servicios, x, fotos, 
				cancelacion, periodos);
		cancelacion = mock(Cancelacion.class);
		periodos = Arrays.asList(mock(Periodo.class), mock(Periodo.class));
	}
	
	@Test
	/**Este test chequea que se halla inicializado correctamente un inmueble*/
	void testInicializacion() {
		Inmueble alquiler = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 3000.0, casa, mediosDePago, servicios, x, fotos,
				cancelacion, periodos, gestionador);
		
		assertEquals(casa, alquiler.getTipoDeInmueble());
		assertEquals(servicios, alquiler.getServicio());
		assertEquals(30.0, alquiler.getSuperficie());
		assertEquals("Argentina", alquiler.getPais());
		assertEquals("Rosario", alquiler.getCiudad());
		assertEquals(3, alquiler.getCapacidad());
		assertEquals("8AM", alquiler.getCheckIn());
		assertEquals("11PM", alquiler.getCheckOut());
		assertEquals(calificaciones, alquiler.getCalificaciones());
		assertEquals(3000.0, alquiler.getPrecioDefault());
		assertEquals(casa, alquiler.getTipoDeInmueble());
		assertEquals(mediosDePago, alquiler.getMediosDePago());
		assertEquals(servicios, alquiler.getServicio());
		assertEquals(x, alquiler.getPropietario());
		assertEquals(x, alquiler.getPropietario());
		assertEquals(cancelacion, alquiler.getPoliticaDeCancelacion());
	}
	
	@Test
	void testCambiarServicios() {
		//setUp
		List<Servicio> nuevosServicios = Arrays.asList(mock(Servicio.class), mock(Servicio.class),
				mock(Servicio.class));
		
		//exercise
		alquiler1.setServicios(nuevosServicios);
		
		//verify
		assertEquals(nuevosServicios, alquiler1.getServicio());
	}
	
	@Test
	void testCambiarPrecio() {
		//exercise
		alquiler1.setPrecioDefault(4000.0);
		
		//verify
		assertEquals(4000.0, alquiler1.getPrecioDefault());
	}
}
