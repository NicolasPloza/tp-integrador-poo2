package ar.edu.unq.poo2.integrador;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class Propietario extends Usuario{
	
	private LocalDate fechaDeIngreso;
	private List<Inmueble> inmuebles;
	
	public Propietario(String nombre, String email, int tel, LocalDate fechaDeIngreso, Sistema sistema) {
		super(nombre,email,tel,sistema);
		this.fechaDeIngreso = fechaDeIngreso;
		this.inmuebles = new ArrayList<Inmueble>();
	}

	public LocalDate getFechaDeIngreso() {

		return this.fechaDeIngreso;
	}

	public void setFechaDeIngreso(LocalDate fecha) {
		
		this.fechaDeIngreso = fecha;
	}
	
	public List<Inmueble> getInmuebles(){
		return this.inmuebles;
	}
	
	public int getAntiguedadEnSistema() {
		
		Period antiguedad = Period.between(fechaDeIngreso, LocalDate.now());
		return antiguedad.getYears();
	}
	
	public void realizarAlta(Inmueble inmueble) {

		this.getInmuebles().add(inmueble);
		this.getSistema().registrarInmueble(inmueble);
	}

	@Override
	public void agregarCalificacion(Calificacion calificacion) {
		if(this.getSistema().tieneCategoriaPara(Calificable.PROPIETARIO, calificacion.getCategoria())) {
			this.getCalificaciones().add(calificacion);
		}
	}
	
	
	
	
	
	

}
