package ar.edu.unq.poo2.integrador.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.integrador.EmailSender;
import ar.edu.unq.poo2.integrador.IEmailSender;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

class EmailSenderTestCase {
	
	private EmailSender email;
	private IEmailSender sender;
	private TipoInmueble tipoDeInmueble;
	private String emailPropietario;
	private String emailInquilino;

	@BeforeEach
	void setUp() {
		this.sender = mock(IEmailSender.class);
		this.email = new EmailSender(this.sender);
		this.tipoDeInmueble = mock(TipoInmueble.class);
		this.emailPropietario = "propietario@mail.com";
		this.emailInquilino = "inquilino@mail.com";
	}

	@Test
	void testSeNotificaLaCancelacionDeUnInmueble() {
		this.email.notificarCancelacion(this.tipoDeInmueble, this.emailPropietario);
		
		verify(this.sender).sendMail(this.emailPropietario, "Cancelación de la reserva", "Se cancelo la reserva");
	}
	
	@Test
	void testSeNotificaLaReservaDeUnInmueble() {
		this.email.notificarReserva(this.emailInquilino);
		
		verify(this.sender).sendMail(this.emailInquilino, "Confirmación de la reserva", "Se acepto la reserva solicitada");
	}
	
	@Test
	void testSeNotificaLaBajaDePrecioDeUnInmueble() {
		this.email.notificarBajaDePrecio(this.tipoDeInmueble, 700000);
		
		verify(this.sender, never()).sendMail(this.emailInquilino, "baja de precio", "el inmueble bajo de precio");
	}

}
