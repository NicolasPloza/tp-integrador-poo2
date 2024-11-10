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
import ar.edu.unq.poo2.integrador.inmueble.Intermedia;
import ar.edu.unq.poo2.integrador.inmueble.MedioDePago;
import ar.edu.unq.poo2.integrador.inmueble.Periodo;
import ar.edu.unq.poo2.integrador.inmueble.PoliticaDeCancelacion;
import ar.edu.unq.poo2.integrador.inmueble.Servicio;
import ar.edu.unq.poo2.integrador.inmueble.SinCancelacion;
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
		gestionador = mock(GestionadorDeNotificaciones.class);
		cancelacion = mock(Cancelacion.class);
		periodos = Arrays.asList(mock(Periodo.class), mock(Periodo.class));
		alquiler1 = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 3000.0, casa, mediosDePago, servicios, x, fotos, 
				cancelacion, periodos, gestionador);
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
		assertEquals(gestionador, alquiler.getGestionadorDeNotificaciones());
		assertEquals(false, alquiler.getAlquilado());
		assertEquals(fotos, alquiler.getFotos());
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
	void testCambiarPrecioAPrecioMayor() {
		//exercise
		alquiler1.setPrecioDefault(4000.0);
		
		//verify
		assertEquals(4000.0, alquiler1.getPrecioDefault());
	}
	
	@Test
	//verifica que se notifique al gestionador de dependencia que se bajo el precio a uno menor al que tenia
	void testCambiarPrecioAPrecioMenorYSeNotifica() {
		//exercise
		alquiler1.setPrecioDefault(1000.0);
		
		//verify
		verify(alquiler1.getGestionadorDeNotificaciones()).notificarBajaDePrecio(casa, 1000.0);
	}
	
	@Test
	void testCostoDeCancelacionParaPoliticaSinCancelacion() {
		//setUp
		Reserva reserva = mock(Reserva.class);
		when(reserva.precioParaFechaElegida()).thenReturn(1000.0);
		
		SinCancelacion sinCancelacion = mock(SinCancelacion.class);
		
		Inmueble casaSinCancelacion = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 3000.0, casa, mediosDePago, servicios, x, fotos, 
				sinCancelacion, periodos, gestionador);
		
		when(sinCancelacion.costo(reserva, casaSinCancelacion)).thenReturn(1000.0);
		
		//verify
		assertEquals(1000.0, casaSinCancelacion.costoDeCancelacion(reserva));
	}
	
	@Test 
	void testCostoDeCancelacionParaPoliticaCancelacion() {
		//setUp
		Reserva reserva = mock(Reserva.class);
		when(reserva.precioParaFechaElegida()).thenReturn(1000.0);
		
		Cancelacion cancelacion = mock(Cancelacion.class);
		
		Inmueble casaConCancelacion = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 3000.0, casa, mediosDePago, servicios, x, fotos, 
				cancelacion, periodos, gestionador);
		
		when(cancelacion.costo(reserva, casaConCancelacion)).thenReturn(100.0);
		
		//verify
		assertEquals(100, casaConCancelacion.costoDeCancelacion(reserva));
	}
	
	@Test
	void testCostoDeCancelacionParaPoliticaIntermedia() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.precioParaFechaElegida()).thenReturn(500.0);
		Intermedia intermedia = mock(Intermedia.class);
		
		Inmueble casaConIntermedia = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 3000.0, casa, mediosDePago, servicios, x, fotos, 
				intermedia, periodos, gestionador);
		
		when(intermedia.costo(reserva, casaConIntermedia)).thenReturn(250.0);
		
		assertEquals(250, casaConIntermedia.costoDeCancelacion(reserva));
	}
	
}
