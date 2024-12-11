package ar.edu.unq.poo2.integrador;

public class Calificacion {
	
	private Categoria categoria;
	private int puntaje;

	public Calificacion(Categoria categoria, int puntaje) {
		this.categoria = categoria;
		this.puntaje = Math.max(1, Math.min(5, puntaje));
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
}
