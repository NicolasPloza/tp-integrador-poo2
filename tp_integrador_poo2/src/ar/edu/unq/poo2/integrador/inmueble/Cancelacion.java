package ar.edu.unq.poo2.integrador.inmueble;

import java.time.LocalDate;

import ar.edu.unq.poo2.integrador.Reserva;

public class Cancelacion implements PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva) {
		double costoAPagar = (LocalDate.now().isBefore(reserva.getFechaInicio().minusDays(10))) ? 0 : (reserva.getInmueble().getPrecioDefault() * 2);
		return costoAPagar;
	}

}
