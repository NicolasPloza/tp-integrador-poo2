package ar.edu.unq.poo2.integrador.inmueble;

import java.time.LocalDate;

import ar.edu.unq.poo2.integrador.Reserva;

public class Cancelacion extends PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva, Inmueble inmueble) {
		if(LocalDate.now().isBefore(reserva.getFechaInicio().minusDays(10))) {
			return inmueble.getPrecioDefault() * 2;
		}
		else {
			return 0;
		}
	}

}
