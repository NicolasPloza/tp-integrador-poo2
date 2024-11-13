package ar.edu.unq.poo2.integrador.inmueble;

public class TipoInmueble {
	
	private String nombre;
	private String descripcion;
	
	public TipoInmueble(String nombre, String descripcion) {
		this.nombre=nombre;
		this.descripcion=descripcion;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Object getDescripcion() {
		return descripcion;
	}

}
