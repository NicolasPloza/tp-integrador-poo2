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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		calificaciones = new ArrayList<Calificacion>();
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
	//verifica que se1|notifique al gestionador de dependencia que se bajo el precio a uno menor al que tenia
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
	
	@Test
	void seAgregaUnaCalificacionAlInmueble() {
		alquiler1.agregarCalificacion(mock(Calificacion.class));
		
		assertEquals(1, alquiler1.getCalificaciones().size());
	}
	
	@Test
	void seCalculaElPromedioDeLaCategoriaBuenTrato() {
		//setUp
		calificaciones = new ArrayList<Calificacion>();
		Categoria buenTrato = mock(Categoria.class);
		when(buenTrato.getNombre()).thenReturn("buen trato");
		Categoria buenTrato1 = mock(Categoria.class);
		when(buenTrato1.getNombre()).thenReturn("buen trato");
		Categoria iluminacion = mock(Categoria.class);
		when(iluminacion.getNombre()).thenReturn("iluminacion");
		
		Calificacion cBuenTrato = mock(Calificacion.class);
		when(cBuenTrato.getCategoria()).thenReturn(buenTrato);
		when(cBuenTrato.getPuntaje()).thenReturn(5);
		Calificacion cBuenTrato1 = mock(Calificacion.class);
		when(cBuenTrato1.getCategoria()).thenReturn(buenTrato);
		when(cBuenTrato1.getPuntaje()).thenReturn(5);
		Calificacion cIluminacion = mock(Calificacion.class);
		when(cIluminacion.getCategoria()).thenReturn(iluminacion);
		
		Inmueble hotel = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 3000.0, casa, mediosDePago, servicios, x, fotos, 
				cancelacion, periodos, gestionador);
		
		hotel.agregarCalificacion(cIluminacion);
		hotel.agregarCalificacion(cBuenTrato1);
		hotel.agregarCalificacion(cBuenTrato);
		
		assertEquals(5, hotel.getPromedio(buenTrato));
	}
	
	@Test
	void seAñadeUnComentarioALaListaDeComentariosDelInmueble() {
		alquiler1.agregarComentario("es un buen hotel");
		
		assertEquals(1, alquiler1.getComentarios().size());
	}
	@Test
	void seObtieneLaListaDePeriodosDeUnInmueble() {
		assertEquals(2,alquiler1.getPeriodos().size());
	}
	
	
	@Test
	void seCalculaElPrecioParaUnPeriodoEntreDosFechas() {
		//setUp
		List<Periodo> periodos1 = new ArrayList<Periodo>();
		Periodo festival = mock(Periodo.class);
		when(festival.getPrecioPorDia()).thenReturn(200.0);
		when(festival.esFechaDePeriodo(null)).thenReturn(true);
		periodos1.add(festival);
		
		Inmueble hotel = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 100.0, casa, mediosDePago, servicios, x, fotos, 
				cancelacion, periodos1, gestionador);
		
		//verify
		assertEquals(600.0, hotel.getPrecioDePeriodo(LocalDate.of(2002, 3, 1), LocalDate.of(2002, 3, 5)));
		//verify(festival).getPrecioPorDia();
		verify(festival).esFechaDePeriodo(mock(LocalDate.class));
	}
	
	@Test
	void seObtieneElPeriodoQueTieneComoFecha() {
		Periodo vacaciones = mock(Periodo.class);
		when(vacaciones.esFechaDePeriodo(LocalDate.of(2002, 6, 3))).thenReturn(true);
		Periodo carnaval = mock(Periodo.class);
		when(carnaval.esFechaDePeriodo(LocalDate.of(2002, 6, 3))).thenReturn(false);
		List<Periodo> periodos1 = Arrays.asList(vacaciones, carnaval);
		
		Inmueble hotel = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM",
				calificaciones, 100.0, casa, mediosDePago, servicios, x, fotos, 
				cancelacion, periodos1, gestionador);
		
		assertEquals(vacaciones, hotel.getPeriodo(LocalDate.of(2002, 6, 3)));
	}
	
	@Test 
	void seCalculaElPromedioTotalDePuntajeDeCategorias() {
		Calificacion buenTrato = mock(Calificacion.class);
		when(buenTrato.getPuntaje()).thenReturn(5);
		Calificacion iluminacion = mock(Calificacion.class);
		when(iluminacion.getPuntaje()).thenReturn(5);
		
		alquiler1.agregarCalificacion(buenTrato);
		alquiler1.agregarCalificacion(iluminacion);
		
		assertEquals(5, alquiler1.getPromedioTotalDePuntajes());
	}
	 
	
}
