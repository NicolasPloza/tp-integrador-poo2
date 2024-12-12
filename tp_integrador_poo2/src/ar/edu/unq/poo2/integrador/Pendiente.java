package ar.edu.unq.poo2.integrador;

public class Pendiente implements EstadoReserva {
	
	private static Pendiente uniqueInstance;
	
	private Pendiente() {
		super();
	}
	
	public static Pendiente getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Pendiente();
		}
		return uniqueInstance;
	}

	@Override
	public void concretar(Reserva reserva) {
		reserva.getPropietario().removerReserva(reserva);
		reserva.notificarReserva();
		reserva.setEstado(Aceptada.getInstance());
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.getPropietario().removerReserva(reserva);
		reserva.getPotencialInquilino().removerReserva(reserva);
		reserva.notificarCancelacion();
		reserva.getInmueble().procesarReservasCondicionalesPara(reserva.getFechaInicio(),reserva.getFechaFin());
		reserva.setEstado(Cancelada.getInstance());
	}

	@Override
	public boolean estaAceptada() {
		return false;
	}

}
