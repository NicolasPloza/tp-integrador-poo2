package ar.edu.unq.poo2.integrador;

public class Aceptada implements EstadoReserva {
	
	private static Aceptada uniqueInstance;
	
	private Aceptada() {
		super();
	}
	
	public static Aceptada getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Aceptada();
		}
		return uniqueInstance;
	}

	@Override
	public void concretar(Reserva reserva) {
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.notificarCancelacion();
		reserva.getPotencialInquilino().removerReserva(reserva);
		reserva.getInmueble().procesarReservasCondicionalesPara(reserva.getFechaInicio(),reserva.getFechaFin());
		reserva.setEstado(Cancelada.getInstance());
	}

	@Override
	public boolean estaAceptada() {
		return true;
	}

	@Override
	public boolean estaCancelada() {
		return false;
	}

}
