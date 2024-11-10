package ar.edu.unq.poo2.integrador;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public interface Interesado {
	
	public void notificarCancelacion(TipoInmueble tipoDeInmueble);
	
	public void notificarReserva();
	
	public void notificarBajaDePrecio(TipoInmueble tipoDeInmueble, float precio);

}
