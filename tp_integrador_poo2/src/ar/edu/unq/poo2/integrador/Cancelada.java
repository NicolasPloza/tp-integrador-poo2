package ar.edu.unq.poo2.integrador;

public class Cancelada implements EstadoReserva {
	
	private static Cancelada uniqueInstance;
	
	private Cancelada() {
		super();
	}
	
	public static Cancelada getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Cancelada();
		}
		return uniqueInstance;
	}

	@Override
	public void concretar(Reserva reserva) {
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		
	}

	@Override
	public boolean estaAceptada() {
		return false;
	}

}
