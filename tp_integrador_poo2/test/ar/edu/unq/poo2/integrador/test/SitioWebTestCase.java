package ar.edu.unq.poo2.integrador.test;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.HomePagePublisher;
import ar.edu.unq.poo2.integrador.SitioWeb;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

class SitioWebTestCase {
	
	private SitioWeb sitio;
	private HomePagePublisher page;
	private TipoInmueble tipoDeInmueble;
	private String emailPropietario;
	private String emailInquilino;

	@BeforeEach
	void setUp() {
		this.page = mock(HomePagePublisher.class);
		this.sitio = new SitioWeb(this.page);
		this.tipoDeInmueble = mock(TipoInmueble.class);
		this.emailPropietario = "propietario@mail.com";
		this.emailInquilino = "inquilino@mail.com";
	}

	@Test
	void testSeNotificaLaCancelacionDeUnInmueble() {
		when(this.tipoDeInmueble.getNombre()).thenReturn("una casa");
		this.sitio.notificarCancelacion(this.tipoDeInmueble, this.emailPropietario);
		
		verify(this.page, never()).publish(this.sitio.mensaje(this.tipoDeInmueble.getNombre(), 1000000));
	}
	
	@Test
	void testSeNotificaLaReservaDeUnInmueble() {
		when(this.tipoDeInmueble.getNombre()).thenReturn("una habitacion");
		this.sitio.notificarReserva(this.emailInquilino);
		
		verify(this.page, never()).publish(this.sitio.mensaje(this.tipoDeInmueble.getNombre(), 10000));
	}
	
	@Test
	void testSeNotificaLaBajaDePrecioDeUnInmueble() {
		when(this.tipoDeInmueble.getNombre()).thenReturn("un duplex");
		this.sitio.notificarBajaDePrecio(this.tipoDeInmueble, 20000);
		
		verify(this.page).publish(this.sitio.mensaje(this.tipoDeInmueble.getNombre(), 20000));
	}

}
