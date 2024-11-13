package ar.edu.unq.poo2.integrador.inmueble;

import java.time.LocalDate;

public class Periodo {

	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double precioPorDia;

	public Periodo(String nombre, LocalDate fechaInicio, LocalDate fechaFin, double precioPorDia) {
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precioPorDia = precioPorDia;
	}
	
	public boolean esFechaDePeriodo(LocalDate fechaAComprobar) {
		return (fechaAComprobar.isAfter(this.getFechaInicio())) && 
			   (fechaAComprobar.isBefore(this.getFechaFin().plusDays(1)));
	}

	public double getPrecioPorDia() {
		return precioPorDia;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
}
