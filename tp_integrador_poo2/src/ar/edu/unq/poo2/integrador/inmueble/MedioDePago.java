package ar.edu.unq.poo2.integrador.inmueble;

public class MedioDePago {

	private String nombre;
	private String descripcion;

	public MedioDePago(String nombre, String descripcion) {
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
