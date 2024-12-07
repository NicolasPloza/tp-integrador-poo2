package ar.edu.unq.poo2.integrador;

import java.util.*;

public abstract class Usuario implements Rankeable{
	
	private String nombre;
	private String email;
	private int telefono;
	private Sistema sistema;
	private List<Calificacion> calificaciones;
	private List<Reserva> reservas;
	
	public Usuario(String nombre, String email, int tel, Sistema sistema) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = tel;
		this.sistema = sistema;
		this.calificaciones = new ArrayList<Calificacion>();
		this.reservas = new ArrayList<Reserva>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public abstract void agregarCalificacion(Calificacion calificacion);

	public List<Calificacion> getCalificaciones() {
	
		return this.calificaciones;
	}

	public double getPromedioTotalDePuntajes() {
		
		long cantidadDePuntajes;
		Double totalPuntajes;
		
		totalPuntajes=calificaciones.stream()
									.mapToDouble( c -> c.getPuntaje() )
									.sum();
		
		cantidadDePuntajes =  calificaciones.stream()
											.count();
		
		double promedio = totalPuntajes/cantidadDePuntajes;
		
		return Math.round(promedio * 100.0) / 100.0;
	}

	
	public double getPromedio(Categoria categoria) {
		
		long cantidadDePuntajes;
		double totalPuntajes;
		
		totalPuntajes=calificaciones.stream()
							.filter( c -> c.getCategoria().getNombre() == categoria.getNombre() )
							.mapToDouble( c -> c.getPuntaje() )
							.sum();
		
		cantidadDePuntajes =  calificaciones.stream()
							.filter( c -> c.getCategoria().getNombre() == categoria.getNombre() )
							.count();
		
		
		double promedio = totalPuntajes/cantidadDePuntajes;
		
		return Math.round(promedio * 100.0) / 100.0;
		
	}

	public void setNombre(String nombre) {
		
		this.nombre = nombre;
	}

	public void setEmail(String email) {
	
		this.email = email;
	}

	public void setTelefono(int tel) {
		
		this.telefono = tel;
	}
	
	public Sistema getSistema() {
		return this.sistema;
	}

	public List<Reserva> getTodasLasReservas() {
		return this.reservas;
	}
}
