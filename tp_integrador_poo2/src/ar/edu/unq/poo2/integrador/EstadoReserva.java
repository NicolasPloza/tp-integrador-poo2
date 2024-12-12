package ar.edu.unq.poo2.integrador;

public interface EstadoReserva {
	
	public void concretar(Reserva reserva);
	
	public void cancelar(Reserva reserva);
	
	public boolean estaAceptada();
	
	public boolean estaCancelada();

}
