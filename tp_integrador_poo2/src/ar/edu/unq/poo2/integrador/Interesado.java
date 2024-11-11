package ar.edu.unq.poo2.integrador;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public interface Interesado {
	
	public void notificarCancelacion(TipoInmueble tipoDeInmueble, String email);
	
	public void notificarReserva(String email);
	
	public void notificarBajaDePrecio(TipoInmueble tipoDeInmueble, double precio);

}
