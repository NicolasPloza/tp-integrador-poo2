package ar.edu.unq.poo2.integrador;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class Calificacion {
	
	private Categoria categoria;
	private int puntaje;

	public Calificacion(Categoria categoria, int puntaje) {
		this.categoria = categoria;
		this.puntaje = puntaje;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
}
