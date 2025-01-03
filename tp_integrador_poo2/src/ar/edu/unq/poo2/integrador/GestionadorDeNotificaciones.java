package ar.edu.unq.poo2.integrador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class GestionadorDeNotificaciones implements Interesado {
	
	private List<Interesado> interesados;
	
	public GestionadorDeNotificaciones() {
		this.interesados=new ArrayList<Interesado>();
	}
	
	public void register(Interesado interesado) {
		this.interesados.add(interesado);
	}
	
	public void unregister(Interesado interesado) {
		this.interesados.remove(interesado);
	}
	
	public boolean tieneInteresados() {
		return !this.interesados.isEmpty();
	}

	@Override
	public void notificarCancelacion(TipoInmueble tipoDeInmueble, String email) {
		this.interesados.stream()
		.forEach(interesado -> interesado.notificarCancelacion(tipoDeInmueble, email));
	}

	@Override
	public void notificarReserva(String email) {
		this.interesados.stream()
		.forEach(interesado -> interesado.notificarReserva(email));
	}

	@Override
	public void notificarBajaDePrecio(TipoInmueble tipoDeInmueble, double precio) {
		this.interesados.stream()
		.forEach(interesado -> interesado.notificarBajaDePrecio(tipoDeInmueble, precio));
	}

}
