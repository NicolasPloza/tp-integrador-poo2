package ar.edu.unq.poo2.integrador.inmueble;

public class TipoInmueble {
	
	private String nombre;
	private String descripcion;
	
	public TipoInmueble(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Object getDescripcion() {
		return descripcion;
	}
	
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof TipoInmueble)) return false;
		TipoInmueble other = (TipoInmueble)o;
		boolean nombreEquals = (this.nombre == null && other.getNombre() == null)
				|| (this.nombre != null && this.nombre.equals(other.getNombre()));
		boolean descripcionEquals = (this.descripcion == null && other.getDescripcion() == null)
				|| (this.descripcion != null && this.descripcion.equals(other.getDescripcion()));
		return nombreEquals && descripcionEquals;
	}
	
	public final int hashCode() {
		int result = 17;
		if (this.nombre != null) result = 31 * result + this.nombre.hashCode();
		if (this.descripcion != null) result = 31 * result + this.descripcion.hashCode();
		return result;
	}

}
