package ar.edu.unq.poo2.integrador.inmueble;

import java.time.LocalDate;

import ar.edu.unq.poo2.integrador.Reserva;

public class Cancelacion implements PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva) {
		if(reserva.getFechaInicio().minusDays(10).isBefore(LocalDate.now())) {
			return reserva.getInmueble().getPrecioDefault() * 2;
		}
		else {
			return 0;
		}
	}

}
