package ar.edu.unq.poo2.integrador.inmueble;

import java.time.LocalDate;

import ar.edu.unq.poo2.integrador.Reserva;

public class Intermedia implements PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva) {
		if(LocalDate.now().isBefore(reserva.getFechaInicio().minusDays(20))) return 0; 
		if(LocalDate.now().isBefore(reserva.getFechaInicio().minusDays(10))) return (reserva.precioParaFechaElegida() * 0.5);
		return reserva.precioParaFechaElegida();
	}

}
