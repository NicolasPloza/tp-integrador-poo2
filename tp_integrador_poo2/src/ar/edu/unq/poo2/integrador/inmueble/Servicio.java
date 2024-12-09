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
	
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Servicio)) return false;
		Servicio other = (Servicio)o;
		boolean nombreEquals = (this.nombre == null && other.getNombre() == null)
				|| (this.nombre != null && this.nombre.equals(other.getNombre()));
		boolean descripcionEquals = (this.descripcion == null && other.getDescripcion() == null)
				|| (this.descripcion != null && this.descripcion.equals(other.getDescripcion()));
		return nombreEquals && descripcionEquals;
	}
	
	public final int hashCode() {
		int result = 18;
		if (this.nombre != null) result = 32 * result + this.nombre.hashCode();
		if (this.descripcion != null) result = 32 * result + this.descripcion.hashCode();
		return result;
	}

}
