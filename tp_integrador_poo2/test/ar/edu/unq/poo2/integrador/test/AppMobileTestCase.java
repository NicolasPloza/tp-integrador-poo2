package ar.edu.unq.poo2.integrador.test;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.AppMobile;
import ar.edu.unq.poo2.integrador.PopUpWindow;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

class AppMobileTestCase {
	
	private AppMobile app;
	private PopUpWindow window;
	private TipoInmueble tipoDeInmueble;
	private String emailPropietario;
	private String emailInquilino;
	private String color;
	private int frontSize;

	@BeforeEach
	void setUp() {
		this.window = mock(PopUpWindow.class);
		this.color = "blue";
		this.frontSize = 12;
		this.app = new AppMobile(this.window, this.color, this.frontSize);
		this.tipoDeInmueble = mock(TipoInmueble.class);
		this.emailPropietario = "propietario@mail.com";
		this.emailInquilino = "inquilino@mail.com";
	}

	@Test
	void testSeNotificaLaCancelacionDeUnInmueble() {
		when(this.tipoDeInmueble.getNombre()).thenReturn("una habitacion");
		this.app.notificarCancelacion(this.tipoDeInmueble, this.emailPropietario);
		
		verify(this.window).popUp(this.app.mensaje(this.tipoDeInmueble.getNombre()), this.color, this.frontSize);
	}
	
	@Test
	void testSeNotificaLaReservaDeUnInmueble() {
		when(this.tipoDeInmueble.getNombre()).thenReturn("una casa");
		this.app.notificarReserva(this.emailInquilino);
		
		verify(this.window, never()).popUp(this.app.mensaje(this.tipoDeInmueble.getNombre()), this.color, this.frontSize);
	}
	
	@Test
	void testSeNotificaLaBajaDePrecioDeUnInmueble() {
		when(this.tipoDeInmueble.getNombre()).thenReturn("uun departamento");
		this.app.notificarBajaDePrecio(this.tipoDeInmueble, 0);
		
		verify(this.window, never()).popUp(this.app.mensaje(this.tipoDeInmueble.getNombre()), this.color, this.frontSize);
	}

}
