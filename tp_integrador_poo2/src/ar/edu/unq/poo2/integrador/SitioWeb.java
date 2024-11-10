package ar.edu.unq.poo2.integrador;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class SitioWeb implements Interesado {
	
	private HomePagePublisher page;
	
	public SitioWeb(HomePagePublisher page) {
		this.page=page;
	}

	@Override
	public void notificarCancelacion(TipoInmueble tipoDeInmueble, String email) {
		
	}

	@Override
	public void notificarReserva(String email) {
		
	}

	@Override
	public void notificarBajaDePrecio(TipoInmueble tipoDeInmueble, double precio) {
		this.page.publish(this.mensaje(tipoDeInmueble.getNombre(), precio));
	}
	
	private String mensaje(String tipoDeInmueble, double precio) {
		return "No te pierdas esta oferta: Un inmueble " + tipoDeInmueble + " a tan sólo " + precio + " pesos";
	}

}
