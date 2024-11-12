package ar.edu.unq.poo2.integrador;

import java.time.LocalDate;
import java.time.Period;

public class Propietario extends Usuario{
	
	private LocalDate fechaDeIngreso;
	
	public Propietario() {
		
	}
	
	public Propietario(String nombre, String email, int tel, LocalDate fechaDeIngreso) {
		super(nombre,email,tel);
		this.fechaDeIngreso = fechaDeIngreso;
	}

	public LocalDate getFechaDeIngreso() {

		return this.fechaDeIngreso;
	}

	public void setFechaDeIngreso(LocalDate fecha) {
		
		this.fechaDeIngreso = fecha;
	}

	public int getAntiguedadEnSistema() {
		
		Period antiguedad = Period.between(fechaDeIngreso, LocalDate.now());
		
		return antiguedad.getYears();
	}
	
	
	
	
	
	

}
