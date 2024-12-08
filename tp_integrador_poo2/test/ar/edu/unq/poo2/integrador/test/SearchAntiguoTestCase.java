package ar.edu.unq.poo2.integrador.test;
import ar.edu.unq.poo2.integrador.*;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.moduloSearch.And;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroCantidadHuespedes;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroPorCiudad;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroPorFecha;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroPrecioMaximo;
import ar.edu.unq.poo2.integrador.moduloSearch.FiltroPrecioMinimo;
import ar.edu.unq.poo2.integrador.moduloSearch.Or;
import ar.edu.unq.poo2.integrador.moduloSearch.Search;

import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SearchAntiguoTestCase {
	
	private Search search;
	private List<Inmueble> inmuebles;
	private Inmueble unInmueble;
	
	private Search filtro5Huesp;
	private Search filtro3Huesp;
	private Search filtroQuilmes;
	private Search filtroCABA;
	
	@BeforeEach
	void setUp() throws Exception {
		inmuebles = new ArrayList<>();
		unInmueble = mock(Inmueble.class);
		inmuebles.add(unInmueble);
		
		filtro5Huesp = new FiltroCantidadHuespedes(5);
		filtro3Huesp = new FiltroCantidadHuespedes(3);
		filtroQuilmes = new FiltroPorCiudad("Quilmes");
		filtroCABA = new FiltroPorCiudad("CABA");
		
	}
	
	
	//-------------pruebas de Search simples (con 1 solo filtro)
	@Test
	void searchConFiltroPorCiudad() {
		//setup
		search = filtroQuilmes;
		
		Inmueble inmuebleDeQuilmes = mock(Inmueble.class); 
		inmuebles.add(inmuebleDeQuilmes);
		when(inmuebleDeQuilmes.getCiudad()).thenReturn("Quilmes");
		when(unInmueble.getCiudad()).thenReturn("Otra");
		
		//exercise
		List<Inmueble> resultado = search.filtrar(inmuebles);
				
		//verify
		assertTrue(resultado.contains(inmuebleDeQuilmes));
		assertFalse(resultado.contains(unInmueble));
		verify(inmuebleDeQuilmes).getCiudad();
			
	}
	
	
	@Test
	void searchConFiltroPorCantidadDeHuespedes() {
		//setup
		search = filtro5Huesp;
		
		Inmueble inmueblePara5 = mock(Inmueble.class); 
		inmuebles.add(inmueblePara5);
		when(inmueblePara5.getCapacidad()).thenReturn(5);
		when(unInmueble.getCapacidad()).thenReturn(3);
		
		//exercise
		List<Inmueble> resultado = search.filtrar(inmuebles);
				
		//verify
		assertTrue(resultado.contains(inmueblePara5));
		assertFalse(resultado.contains(unInmueble));
		verify(inmueblePara5).getCapacidad();
			
	}
	
	@Test
	void searchConFiltroPorFecha() {
		//setup
		Sistema sistema = mock(Sistema.class);
		LocalDate fechaEntrada = LocalDate.of(2024,8,24);
		LocalDate fechaSalida = LocalDate.of(2024,9,2);
		Inmueble inmuebleDisponible = mock(Inmueble.class); 
		Inmueble inmuebleOcupado = mock(Inmueble.class); 
		search = new FiltroPorFecha(fechaEntrada , fechaSalida, sistema);
		
		
		when(sistema.estaDisponible(inmuebleDisponible, fechaEntrada, fechaSalida)).thenReturn(true);
		when(sistema.estaDisponible(inmuebleOcupado, fechaEntrada, fechaSalida)).thenReturn(false);
		inmuebles.add(inmuebleDisponible);
		inmuebles.add(inmuebleOcupado);
		
	
		//exercise
		List<Inmueble> resultado = search.filtrar(inmuebles);
				
		//verify
		assertTrue(resultado.contains(inmuebleDisponible));
		assertFalse(resultado.contains(inmuebleOcupado));
		verify(sistema).estaDisponible(inmuebleDisponible, fechaEntrada, fechaSalida);
			
	}
	
	
	@Test
	void searchConFiltroPorPrecioMinimo() {
		//setup
		
		search = new FiltroPrecioMinimo(100);
		
		
		Inmueble inmuebleEnPrecio = mock(Inmueble.class); 
		Inmueble inmuebleFueraDePrecio = mock(Inmueble.class); 
		inmuebles.add(inmuebleEnPrecio);
		inmuebles.add(inmuebleFueraDePrecio);
		when(inmuebleEnPrecio.getPrecioDefault()).thenReturn(150.00);
		when(inmuebleFueraDePrecio.getPrecioDefault()).thenReturn(50.00);
		
		//exercise
		List<Inmueble> resultado = search.filtrar(inmuebles);
				
		//verify
		assertTrue(resultado.contains(inmuebleEnPrecio));
		assertFalse(resultado.contains(inmuebleFueraDePrecio));
		verify(inmuebleEnPrecio).getPrecioDefault();
		verify(inmuebleFueraDePrecio).getPrecioDefault();
			
	}
	
	@Test
	void searchConFiltroPorPrecioMaximo() {
		//setup
		
		search = new FiltroPrecioMaximo(100);
		
		
		Inmueble inmuebleEnPrecio = mock(Inmueble.class); 
		Inmueble inmuebleFueraDePrecio = mock(Inmueble.class); 
		inmuebles.add(inmuebleEnPrecio);
		inmuebles.add(inmuebleFueraDePrecio);
		when(inmuebleEnPrecio.getPrecioDefault()).thenReturn(50.00);
		when(inmuebleFueraDePrecio.getPrecioDefault()).thenReturn(150.00);
		
		//exercise
		List<Inmueble> resultado = search.filtrar(inmuebles);
				
		//verify
		assertTrue(resultado.contains(inmuebleEnPrecio));
		assertFalse(resultado.contains(inmuebleFueraDePrecio));
		verify(inmuebleEnPrecio).getPrecioDefault();
		verify(inmuebleFueraDePrecio).getPrecioDefault();
			
	}
	
	
	
	//--------pruebas de Search compuesto(mas de un filtro o combinados con operadores )
	
	@Test
	void searchCompuesto_OperadorAND_ConDosFiltros() {
		//setup
		search = new And(filtro5Huesp ,filtroQuilmes);
		
		Inmueble inmuebleDeQuilmesPara5 = mock(Inmueble.class);
		inmuebles.add(inmuebleDeQuilmesPara5);
		
		when(inmuebleDeQuilmesPara5.getCapacidad()).thenReturn(5);
		when(inmuebleDeQuilmesPara5.getCiudad()).thenReturn("Quilmes");
		
			//inmueble de quilmes pero para 3 huespedes
		when(unInmueble.getCapacidad()).thenReturn(3);
		when(unInmueble.getCiudad()).thenReturn("Quilmes");
		
		//exercise	
		List<Inmueble> resultado = search.filtrar(inmuebles);
		
		//verify
		assertTrue(resultado.contains(inmuebleDeQuilmesPara5));
		assertFalse(resultado.contains(unInmueble));
		
		verify(inmuebleDeQuilmesPara5).getCiudad();
		verify(inmuebleDeQuilmesPara5).getCapacidad();
	}
	
	
	@Test
	void searchCompuesto_OperadorOR_ConDosFiltros() {
		//setup
		search = new Or(filtro5Huesp,filtroQuilmes);
		
		Inmueble inmuebleDeQuilmesPara5 = mock(Inmueble.class);
		Inmueble inmuebleDeQuilmesPara2 = mock(Inmueble.class);
		Inmueble inmuebleDeCABAPara5 = mock(Inmueble.class);
		inmuebles.add(inmuebleDeQuilmesPara5);
		inmuebles.add(inmuebleDeQuilmesPara2);
		inmuebles.add(inmuebleDeCABAPara5);
		
			// inmuebles que cumplen busqueda
		when(inmuebleDeQuilmesPara5.getCiudad()).thenReturn("Quilmes");
		when(inmuebleDeQuilmesPara5.getCapacidad()).thenReturn(5);
		
		when(inmuebleDeQuilmesPara2.getCiudad()).thenReturn("Quilmes");
		when(inmuebleDeQuilmesPara2.getCapacidad()).thenReturn(2);
		
		when(inmuebleDeCABAPara5.getCiudad()).thenReturn("CABA");
		when(inmuebleDeCABAPara5.getCapacidad()).thenReturn(5);
			
			//inmuebles que no cumplen busqueda
		when(unInmueble.getCapacidad()).thenReturn(3);
		when(unInmueble.getCiudad()).thenReturn("Otra");
		
		
		//exercise	
		List<Inmueble> resultado = search.filtrar(inmuebles);
		
		//verify
		assertTrue(resultado.contains(inmuebleDeQuilmesPara5));
		assertTrue(resultado.contains(inmuebleDeQuilmesPara2));
		assertTrue(resultado.contains(inmuebleDeCABAPara5));
		assertFalse(resultado.contains(unInmueble));
		
		
	}
	
	
	@Test
	void searchCompuesto_combinadoConSearchCompuestosYFiltros() {
		//setup
		search = new Or(new And(filtro5Huesp,filtroQuilmes), new And(filtroCABA,filtro3Huesp));
		
		Inmueble inmuebleDeQuilmesPara5 = mock(Inmueble.class);
		Inmueble inmuebleDeCABAPara3 = mock(Inmueble.class);
		Inmueble inmuebleDeMerloPara5 = mock(Inmueble.class);
		inmuebles.add(inmuebleDeQuilmesPara5);
		inmuebles.add(inmuebleDeCABAPara3);
		inmuebles.add(inmuebleDeMerloPara5);
		
		//----------inmuebles que cumplen busqueda
		when(inmuebleDeQuilmesPara5.getCiudad()).thenReturn("Quilmes");
		when(inmuebleDeQuilmesPara5.getCapacidad()).thenReturn(5);
		
		when(inmuebleDeCABAPara3.getCiudad()).thenReturn("CABA");
		when(inmuebleDeCABAPara3.getCapacidad()).thenReturn(3);
		
		//---------inmuebles que no cumplen busqueda
		when(unInmueble.getCiudad()).thenReturn("Otra");
		when(unInmueble.getCapacidad()).thenReturn(3);
		when(inmuebleDeMerloPara5.getCiudad()).thenReturn("Merlo");
		when(inmuebleDeMerloPara5.getCapacidad()).thenReturn(5);
		
		//exercise	
		List<Inmueble> resultado = search.filtrar(inmuebles);
		
		//verify
		assertTrue(resultado.contains(inmuebleDeQuilmesPara5));
		assertTrue(resultado.contains(inmuebleDeCABAPara3));
		
		assertFalse(resultado.contains(inmuebleDeMerloPara5));
		assertFalse(resultado.contains(unInmueble));
		
		verify(inmuebleDeQuilmesPara5,atLeastOnce()).getCiudad();
		verify(inmuebleDeQuilmesPara5,atLeastOnce()).getCapacidad();
		verify(inmuebleDeCABAPara3,atLeastOnce()).getCiudad();
		verify(inmuebleDeCABAPara3,atLeastOnce()).getCapacidad();
		verify(inmuebleDeMerloPara5,atLeastOnce()).getCiudad();
		verify(inmuebleDeMerloPara5,atLeastOnce()).getCapacidad();
		verify(unInmueble,atLeastOnce()).getCiudad();
		verify(unInmueble,atLeastOnce()).getCapacidad();
	}
	
	
	
	@Test 
	void seRealizaUnaBusquedaSobreUnaListaVacia_SeDevuelveUnaListaVacia() {
		search = new Or(new And(filtro5Huesp,filtroQuilmes), new And(filtroCABA,filtro3Huesp)); 
		List<Inmueble> listaInmuebles = new ArrayList<>();
		
		List<Inmueble> resultado = search.filtrar(listaInmuebles);
		
		assertTrue(resultado.isEmpty());
		
	}
	
	@Test 
	void seRealizaUnaBusquedaYNingunInmuebleCumpleLaCondicion_SeDevuelveUnaListaVacia() {
		search = new Or(new And(filtro5Huesp,filtroQuilmes), new And(filtroCABA,filtro3Huesp)); 
		
		Inmueble inmuebleDeCABAPara8 = mock(Inmueble.class);
		Inmueble inmuebleDeMerloPara5 = mock(Inmueble.class);
		inmuebles.add(inmuebleDeCABAPara8);
		inmuebles.add(inmuebleDeMerloPara5);
		
		
		when(inmuebleDeCABAPara8.getCiudad()).thenReturn("CABA");
		when(inmuebleDeCABAPara8.getCapacidad()).thenReturn(8);
		when(unInmueble.getCiudad()).thenReturn("Otra");
		when(unInmueble.getCapacidad()).thenReturn(3);
		when(inmuebleDeMerloPara5.getCiudad()).thenReturn("Merlo");
		when(inmuebleDeMerloPara5.getCapacidad()).thenReturn(5);
		
		List<Inmueble> resultado = search.filtrar(inmuebles);
		
		assertTrue(resultado.isEmpty());
		
	}
	
	
	
	
}
