package ar.edu.unq.poo2.integrador.inmueble;

public class Servicio {

	private String nombre;
	private String descripcion;

	public Servicio(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

}
