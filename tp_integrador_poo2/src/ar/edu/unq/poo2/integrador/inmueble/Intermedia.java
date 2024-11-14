package ar.edu.unq.poo2.integrador.inmueble;

import java.time.LocalDate;

import ar.edu.unq.poo2.integrador.Reserva;

public class Intermedia implements PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva, Inmueble inmueble) {
		if(LocalDate.now().isBefore(reserva.getFechaInicio().minusDays(20))) {
			return 0;
		} else if(this.estaDentroDelIntervaloAceptable(reserva)) {
			return reserva.precioParaFechaElegida() / 2;
		} else {
			return reserva.precioParaFechaElegida();
		}
	}

	private boolean estaDentroDelIntervaloAceptable(Reserva reserva) {
		return (LocalDate.now().isBefore(reserva.getFechaInicio().minusDays(10)) 
				||
				(LocalDate.now().isBefore(reserva.getFechaInicio().minusDays(19))));
	}

}
