package ar.edu.unq.poo2.integrador.inmueble;

import ar.edu.unq.poo2.integrador.Reserva;

public class SinCancelacion implements PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva, Inmueble casaSinCancelacion) {
		return reserva.precioParaFechaElegida();
	}

}
