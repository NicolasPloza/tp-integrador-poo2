package ar.edu.unq.poo2.integrador;

public interface Rankeable {
	
	public void agregarCalificacion(Calificacion calificacion);
	
	public double getPromedio(Categoria categoria);
	
	public double getPromedioTotalDePuntajes();
}
