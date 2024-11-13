package ar.edu.unq.poo2.integrador;

import java.util.*;

public class Usuario implements Rankeable{
	
	private String nombre;
	private String email;
	private int telefono;
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String email, int tel) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = tel;
				
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

	public void agregarCalificacion(Calificacion calificacion) {
		
		calificaciones.add(calificacion);
	}

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

	
	

}
