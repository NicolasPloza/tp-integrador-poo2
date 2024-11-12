package ar.edu.unq.poo2.integrador;

public interface Rankeable {
	
	public void agregarCalificacion(Calificacion calificacion);
	
	public double getPromedio(String nombreDeCategoria);
	
	public double getPromedioTotalDePuntajes();
}
