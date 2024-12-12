package ar.edu.unq.poo2.integrador;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class Propietario extends Usuario{
	
	private LocalDate fechaDeIngreso;
	private List<Inmueble> inmuebles;
	
	public Propietario(String nombre, String email, int tel, Sistema sistema) {
		super(nombre,email,tel,sistema);
		this.fechaDeIngreso = LocalDate.now();
		this.inmuebles = new ArrayList<Inmueble>();
	}

	public LocalDate getFechaDeIngreso() {

		return this.fechaDeIngreso;
	}
	
	public List<Inmueble> getInmuebles(){
		return this.inmuebles;
	}
	
	public int getAntiguedadEnSistema() {
		
		Period antiguedad = Period.between(fechaDeIngreso, LocalDate.now());
		return antiguedad.getYears();
	}
	
	public void realizarAlta(Inmueble inmueble) {
		this.getSistema().registrarInmueble(inmueble);
		if(this.getSistema().acepta(inmueble.getTipoDeInmueble(), inmueble.getServicios())) this.getInmuebles().add(inmueble);
	}
	
	@Override
	public void agregarCalificacion(Calificacion calificacion) {
		if(this.getSistema().tieneCategoriaPara(Calificable.PROPIETARIO, calificacion.getCategoria())) {
			this.getCalificaciones().add(calificacion);
		}
	}

	public int getCantidadDeInmuebles() {
		return this.getInmuebles().size();
	}

	public int cantidadTotalDeAlquileres() {
		
		return  this.getInmuebles()
					.stream()
					.mapToInt(i -> i.cantidadDeAlquileres())
					.sum() ; 
	}

	@Override
	public boolean esInquilino() {
		return false;
	}

	@Override
	public boolean esPropietario() {
		return true;
	}

	@Override
	public int getCantidadDeAlquileres() {
		return this.inmuebles.stream()
							 .mapToInt( i -> i.cantidadDeAlquileres())
							 .sum();
	}

	
}
