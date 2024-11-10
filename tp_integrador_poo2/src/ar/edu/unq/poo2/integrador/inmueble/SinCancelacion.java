package ar.edu.unq.poo2.integrador.inmueble;

import ar.edu.unq.poo2.integrador.test.Reserva;

public class SinCancelacion extends PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva, Inmueble casaSinCancelacion) {
		return reserva.precioParaFechaElegida();
	}

}
