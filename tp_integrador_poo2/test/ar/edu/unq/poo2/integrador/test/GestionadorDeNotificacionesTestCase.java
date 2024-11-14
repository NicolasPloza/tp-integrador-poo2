package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.GestionadorDeNotificaciones;
import ar.edu.unq.poo2.integrador.Interesado;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

class GestionadorDeNotificacionesTestCase {
	
	private GestionadorDeNotificaciones gestionador;
	private Interesado interesado;
	private String emailPropietario;
	private String emailInquilino;
	private TipoInmueble tipoDeInmueble;

	@BeforeEach
	void setUp() {
		this.interesado = mock(Interesado.class);
		this.gestionador = new GestionadorDeNotificaciones();
		this.tipoDeInmueble = mock(TipoInmueble.class);
		this.emailPropietario = "propietario@mail.com";
		this.emailInquilino = "inquilino@mail.com";
	}

	@Test
	void testSeRegistraUnInteresadoEnElGestionador() {
		this.gestionador.register(this.interesado);
		
		assertTrue(this.gestionador.tieneInteresados());
	}
	
	@Test
	void testSeQuitaUnInteresadoEnElGestionador() {
		this.gestionador.register(interesado);
		this.gestionador.unregister(interesado);
		
		assertFalse(this.gestionador.tieneInteresados());
	}
	
	@Test
	void testSeNotificaLaCancelacionDeUnInmuebleATodosLosIntereados() {
		this.gestionador.register(this.interesado);
		this.gestionador.notificarCancelacion(this.tipoDeInmueble, this.emailPropietario);
		
		verify(this.interesado).notificarCancelacion(this.tipoDeInmueble, this.emailPropietario);
	}
	
	@Test
	void testSeNotificaLaReservaDeUnInmuebleATodosLosInteresados() {
		this.gestionador.register(this.interesado);
		this.gestionador.notificarReserva(this.emailInquilino);
		
		verify(this.interesado).notificarReserva(this.emailInquilino);
	}
	
	@Test
	void testSeNotificaLaBajaDePrecioDeUnInmuebleATodosLosInteresados() {
		this.gestionador.register(this.interesado);
		this.gestionador.notificarBajaDePrecio(this.tipoDeInmueble, 12333);
		
		verify(this.interesado).notificarBajaDePrecio(this.tipoDeInmueble, 12333);
	}

}
