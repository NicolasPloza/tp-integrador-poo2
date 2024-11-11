package ar.edu.unq.poo2.integrador.inmueble;

import ar.edu.unq.poo2.integrador.Reserva;

public class Cancelacion extends PoliticaDeCancelacion {

	@Override
	public double costo(Reserva reserva, Inmueble inmueble) {
		return 0;
	}

}
