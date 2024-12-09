package ar.edu.unq.poo2.integrador;

import java.time.LocalDate;
import java.util.List;


public class Inquilino extends Usuario {

	public Inquilino(String nombre, String email, int tel, Sistema sistema) {
		super(nombre, email, tel, sistema);
	}

	@Override
	public void agregarCalificacion(Calificacion calificacion) {
		if(this.getSistema().tieneCategoriaPara(Calificable.INQUILINO, calificacion.getCategoria())) {
			this.getCalificaciones().add(calificacion);
		}
	}
	
	public List<Reserva> getReservasFuturas() {
		List<Reserva> reservas = this.getTodasLasReservas().stream()
				.filter(reserva -> reserva.getFechaInicio().isAfter(LocalDate.now()))
				.toList();
		return reservas;
	}
	
	public List<Reserva> getReservasDeCiudad(String ciudad) {
		List<Reserva> reservas = this.getTodasLasReservas().stream()
				.filter(reserva -> reserva.getInmueble().getCiudad().equals(ciudad))
				.toList();
		return reservas;
	}
	
	public List<String> getCiudadesDeReservas() {
		List<String> ciudades = this.getTodasLasReservas().stream()
				.map(reserva -> reserva.getInmueble().getCiudad())
				.toList();
		return ciudades;
	}

	public long getCantidadDeAlquileres() {
		
		return this.getTodasLasReservas().stream().filter(r -> r.estaFinalizada()).count() ;
	}
	
}
