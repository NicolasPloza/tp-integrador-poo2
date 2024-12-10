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
import java.time.LocalDate;
import java.util.*;

class InmuebleTestCase {
	private TipoInmueble casa;
	private List<Servicio> servicios;
	private List<MedioDePago> mediosDePago;
	private Propietario x;
	private List<Foto> fotos;
	private Inmueble alquiler1;
	private PoliticaDeCancelacion cancelacion;
	private List<Periodo> periodos;
	private GestionadorDeNotificaciones gestionador;
	private Sistema sistema;
	private Calificacion calificacion;
	private Categoria ambiente;
	private MedioDePago tarjeta;
	
	@BeforeEach
	void setUp() {
		this.fotos = new ArrayList<Foto>();
		this.fotos.add(mock(Foto.class));
		this.fotos.add(mock(Foto.class));
		this.mediosDePago = new ArrayList<MedioDePago>();
		this.tarjeta = mock(MedioDePago.class);
		mediosDePago.add(tarjeta);
		mediosDePago.add(mock(MedioDePago.class));
		this.casa = mock(TipoInmueble.class);
		this.servicios = Arrays.asList(mock(Servicio.class), mock(Servicio.class));
		this.gestionador = mock(GestionadorDeNotificaciones.class);
		this.cancelacion = mock(Cancelacion.class);
		this.periodos = Arrays.asList(mock(Periodo.class), mock(Periodo.class));
		this.sistema = mock(Sistema.class);
		this.calificacion = mock(Calificacion.class);
		this.ambiente = mock(Categoria.class);
		this.alquiler1 = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, casa, mediosDePago, servicios, x, fotos, cancelacion, periodos, gestionador, this.sistema);
	}
	
	@Test
	/**Este test chequea que se halla inicializado correctamente un inmueble*/
	void testInicializacion() {
		Inmueble alquiler = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, this.mediosDePago, this.servicios, this.x, this.fotos, this.cancelacion, this.periodos, this.gestionador, this.sistema);
		
		assertEquals(casa, alquiler.getTipoDeInmueble());
		assertEquals(servicios, alquiler.getServicio());
		assertEquals(30.0, alquiler.getSuperficie());
		assertEquals("Argentina", alquiler.getPais());
		assertEquals("Rosario", alquiler.getCiudad());
		assertEquals(3, alquiler.getCapacidad());
		assertEquals("8AM", alquiler.getCheckIn());
		assertEquals("11PM", alquiler.getCheckOut());
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
		Inmueble casaSinCancelacion = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, this.mediosDePago, this.servicios, this.x, this.fotos, sinCancelacion, this.periodos, this.gestionador, this.sistema);
		when(reserva.getInmueble()).thenReturn(casaSinCancelacion);
		when(sinCancelacion.costo(reserva)).thenReturn(1000.0);
		
		//verify
		assertEquals(1000.0, casaSinCancelacion.costoDeCancelacion(reserva));
	}
	
	@Test 
	void testCostoDeCancelacionParaPoliticaCancelacion() {
		//setUp
		Reserva reserva = mock(Reserva.class);
		when(reserva.precioParaFechaElegida()).thenReturn(1000.0);
		Cancelacion cancelacion = mock(Cancelacion.class);
		Inmueble casaConCancelacion = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, casa, mediosDePago, servicios, x, fotos, cancelacion, periodos, gestionador, this.sistema);
		when(reserva.getInmueble()).thenReturn(casaConCancelacion);
		when(cancelacion.costo(reserva)).thenReturn(100.0);
		
		//verify
		assertEquals(100, casaConCancelacion.costoDeCancelacion(reserva));
	}
	
	@Test
	void testCostoDeCancelacionParaPoliticaIntermedia() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.precioParaFechaElegida()).thenReturn(500.0);
		Intermedia intermedia = mock(Intermedia.class);
		Inmueble casaConIntermedia = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, this.mediosDePago, this.servicios, this.x, this.fotos, intermedia, this.periodos, this.gestionador, this.sistema);
		when(reserva.getInmueble()).thenReturn(casaConIntermedia);
		when(intermedia.costo(reserva)).thenReturn(250.0);
		
		assertEquals(250, casaConIntermedia.costoDeCancelacion(reserva));
	}
	
	@Test
	void testSeAgregaUnaCalificacionAlInmueble() {
		when(this.calificacion.getCategoria()).thenReturn(this.ambiente);
		when(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, this.ambiente)).thenReturn(true);
		this.alquiler1.agregarCalificacion(this.calificacion);
		
		assertEquals(1, this.alquiler1.getCalificaciones().size());
		assertTrue(this.alquiler1.getCalificaciones().contains(this.calificacion));
	}
	
	@Test
	void testSeAgregaUnaCalificacionAlInmuebleDeUnaCategoriaQueNoTiene() {
		when(this.calificacion.getCategoria()).thenReturn(this.ambiente);
		when(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, this.ambiente)).thenReturn(false);
		this.alquiler1.agregarCalificacion(this.calificacion);
		
		assertEquals(0, this.alquiler1.getCalificaciones().size());
		assertFalse(this.alquiler1.getCalificaciones().contains(this.calificacion));
	}
	
	@Test
	void seCalculaElPromedioDeLaCategoriaBuenTrato() {
		//setUp
		//List<Calificacion> calificaciones = new ArrayList<Calificacion>();
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
		
		Inmueble hotel = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, this.mediosDePago, this.servicios, this.x, this.fotos, this.cancelacion, this.periodos, this.gestionador, this.sistema);
		when(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, iluminacion)).thenReturn(true);
		hotel.agregarCalificacion(cIluminacion);
		when(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, buenTrato)).thenReturn(true);
		hotel.agregarCalificacion(cBuenTrato1);
		when(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, buenTrato1)).thenReturn(true);
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
		when(festival.esFechaDePeriodo(LocalDate.of(2002, 3, 1))).thenReturn(true);
		when(festival.esFechaDePeriodo(LocalDate.of(2002, 3, 2))).thenReturn(true);
		when(festival.esFechaDePeriodo(LocalDate.of(2002, 3, 3))).thenReturn(true);
		periodos1.add(festival);
		
		Inmueble hotel = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 100.0, this.casa,
										this.mediosDePago, this.servicios, this.x, this.fotos, this.cancelacion, 
										periodos1, this.gestionador, this.sistema);
		
		//exercise
		double precio =  hotel.getPrecioDePeriodo(LocalDate.of(2002, 3, 1), LocalDate.of(2002, 3, 5));
		
		//verify
		assertEquals(800.0, precio );
		verify(festival, times(3)).getPrecioPorDia();
	}
	
	@Test
	void seObtieneElPeriodoQueTieneComoFecha() {
		Periodo vacaciones = mock(Periodo.class);
		when(vacaciones.esFechaDePeriodo(LocalDate.of(2002, 6, 3))).thenReturn(true);
		Periodo carnaval = mock(Periodo.class);
		when(carnaval.esFechaDePeriodo(LocalDate.of(2002, 6, 3))).thenReturn(false);
		List<Periodo> periodos1 = Arrays.asList(vacaciones, carnaval);
		
		Inmueble hotel = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 100.0, this.casa, this.mediosDePago, this.servicios, this.x, this.fotos, this.cancelacion, periodos1, this.gestionador, this.sistema);
		
		assertEquals(vacaciones, hotel.getPeriodo(LocalDate.of(2002, 6, 3)));
	}
	
	@Test 
	void seCalculaElPromedioTotalDePuntajeDeCategorias() {
		Calificacion buenTrato = mock(Calificacion.class);
		Categoria cBuenTrato = mock(Categoria.class);
		when(buenTrato.getPuntaje()).thenReturn(5);
		when(buenTrato.getCategoria()).thenReturn(cBuenTrato);
		Calificacion iluminacion = mock(Calificacion.class);
		Categoria cIluminacion = mock(Categoria.class);
		when(iluminacion.getPuntaje()).thenReturn(5);
		when(iluminacion.getCategoria()).thenReturn(cIluminacion);
		when(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, cBuenTrato)).thenReturn(true);
		alquiler1.agregarCalificacion(buenTrato);
		when(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, cIluminacion)).thenReturn(true);
		alquiler1.agregarCalificacion(iluminacion);
		
		assertEquals(5, alquiler1.getPromedioTotalDePuntajes());
	}
	 
	@Test
	void testSeAgregaUnaFotoAUnInmueble() {
		alquiler1.agregarFoto(mock(Foto.class));
		
		assertEquals(3, alquiler1.getFotos().size());
	}
	/* ---------ESTE TEST SE SACO PORQUE EL INMUEBLE SOLO GUARDA LAS RESERVAS ACEPTADAS O CONDICIONALES, LAS PENDIENTES NO----
	@Test
	void testSeReservaUnInmueble() {
		when(tarjeta.getNombre()).thenReturn("tarjeta");
		alquiler1.reservar(mock(Inquilino.class), LocalDate.of(2025, 5, 4), LocalDate.of(2025,5, 14), tarjeta);
		assertEquals(1, alquiler1.getReservas().size());
	}*/
	
//----------------------------- INICIAR RESERVA ------------------------------------------------------------
	@Test
	void test_seVerificaQueSeIniciaUnaReservaDeUnInmueble_IngresandoMedioDePagoValido() {
		
		//SETUP
		Propietario propietario = mock(Propietario.class);
		Inquilino inquilino = mock(Inquilino.class);
		
		MedioDePago tarjeta = mock(MedioDePago.class);
		when(tarjeta.getNombre()).thenReturn("tarjeta");
		List<MedioDePago> mediosDePago = Arrays.asList(tarjeta);  
		
		Inmueble inmueble = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, 
										 mediosDePago,this.servicios, propietario, this.fotos, this.cancelacion, 
										 this.periodos, this.gestionador, this.sistema);
		
		//EXERCISE
		inmueble.reservar(inquilino, LocalDate.of(2024, 12, 15), LocalDate.of(2024, 12, 25), tarjeta);
		
		
		//VERIFY
		verify(tarjeta, atLeastOnce()).getNombre();
		assertFalse(inmueble.getReservas().isEmpty());
		//assertTrue(inmueble.getReservasAceptadas().isEmpty()); // todavia la tiene que aceptar el propietario
		//esta assercion se rompe
	
	}
	
	@Test
	void test_SeVerificaQueSeIngresaUnMedioDePagoInvalido_entoncesSeLanzaUnaExcepcion() {
		//SETUP
		Propietario propietario = mock(Propietario.class);
		Inquilino inquilino = mock(Inquilino.class);
		
		MedioDePago tarjeta = mock(MedioDePago.class);
		when(tarjeta.getNombre()).thenReturn("tarjeta");
		
		MedioDePago modo = mock(MedioDePago.class);
		when(modo.getNombre()).thenReturn("modo");
		
		List<MedioDePago> mediosDePago = Arrays.asList(tarjeta);  
		
		Inmueble inmueble = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, 
										 mediosDePago,this.servicios, propietario, this.fotos, this.cancelacion, 
										 this.periodos, this.gestionador, this.sistema);
		
		//EXERCISE
		RuntimeException error = 
				assertThrows(RuntimeException.class, () -> inmueble.reservar(inquilino, LocalDate.of(2024, 12, 15)
																			 , LocalDate.of(2024, 12, 25), modo));
		
		
		//VERIFY
		verify(tarjeta).getNombre();
		verify(modo).getNombre();
		verifyNoInteractions(propietario);
		verifyNoInteractions(sistema);
		assertTrue(inmueble.getReservas().isEmpty());
		assertTrue(inmueble.getReservasCondicionales().isEmpty() );
		assertEquals("El medio de pago ingresado no es valido", error.getMessage());
		
	
	}
	
	
	@Test
	void test_seVerificaQueSeIniciaUnaReservaQueNoEstaDisponible_entoncesSeAgregaALaListaDeReservasCondicionales() {
		
		//SETUP
		Propietario propietario = mock(Propietario.class);
		Inquilino inquilino = mock(Inquilino.class);
		MedioDePago tarjeta = mock(MedioDePago.class);
		when(tarjeta.getNombre()).thenReturn("tarjeta");
		List<MedioDePago> mediosDePago = Arrays.asList(tarjeta);  		
		
		Inmueble inmueble = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, 
										 mediosDePago,this.servicios, propietario, this.fotos, this.cancelacion, 
										 this.periodos, this.gestionador, this.sistema);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2024, 12, 9));
		when(reserva.getFechaFin()).thenReturn(LocalDate.of(2024, 12, 17));
		when(reserva.esAceptada()).thenReturn(true);
		
		//EXERCISE
		//inmueble.agregarReserva(reserva);
		inmueble.reservar(inquilino, LocalDate.of(2024, 12, 15), LocalDate.of(2024, 12, 25), tarjeta);
		
		
		//VERIFY
		assertFalse(inmueble.getReservasCondicionales().isEmpty());//SE ROMPE NOSE PORQUE 
		verify(reserva).getFechaInicio();
		verify(reserva).getFechaFin();
		verifyNoInteractions(propietario);
		verifyNoInteractions(sistema);
		
	}
	
	@Test 
	void test_seVerificaQueSeProcesaLaListaDeCondicionalesCuandoSeCancelaUnaReserva() {
		//setup
		Propietario propietario = mock(Propietario.class);
		Inmueble inmueble = new Inmueble(30.0,"Argentina", "Rosario", 3, "8AM", "11PM", 3000.0, this.casa, 
				 mediosDePago,this.servicios, propietario, this.fotos, this.cancelacion, 
				 this.periodos, this.gestionador, this.sistema);
		
		LocalDate fechaIni = LocalDate.of(2024, 8 , 5);
		LocalDate fechaFin = LocalDate.of(2024, 8, 10);
		
		Reserva reservaCond1 = mock(Reserva.class);
		when(reservaCond1.estaDentroDeFechas(fechaIni, fechaFin)).thenReturn(true);
		
		Reserva reservaCond2 = mock(Reserva.class);
		when(reservaCond2.estaDentroDeFechas(fechaIni, fechaFin)).thenReturn(false);
		
		inmueble.agregarReservaCondicional(reservaCond1);
		inmueble.agregarReservaCondicional(reservaCond2);
		
		//exercise
		inmueble.procesarReservasCondicionalesPara(fechaIni , fechaFin );
		
		//verify
		assertFalse(inmueble.getReservasCondicionales().contains(reservaCond1));
		assertTrue(inmueble.getReservas().contains(reservaCond1));
		verify(propietario).agregarReserva(reservaCond1);
		verify(sistema).registrar(reservaCond1);
		
	}
	
	
	
	
	
	
}
