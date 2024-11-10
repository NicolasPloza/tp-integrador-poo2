package ar.edu.unq.poo2.integrador;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class EmailSender implements Interesado {
	
	private IEmailSender sender;
	
	public EmailSender(IEmailSender emailSender) {
		this.sender=emailSender;
	}

	@Override
	public void notificarCancelacion(TipoInmueble tipoDeInmueble, String email) {
		sender.sendMail(email, "Cancelación de la reserva", "Se cancelo la reserva");
	}

	@Override
	public void notificarReserva(String email) {
		sender.sendMail(email, "Confirmación de la reserva", "Se acepto la reserva solicitada");
	}

	@Override
	public void notificarBajaDePrecio(TipoInmueble tipoDeInmueble, double precio) {
		
	}

}
