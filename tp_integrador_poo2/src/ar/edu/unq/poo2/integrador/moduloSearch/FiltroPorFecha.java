package ar.edu.unq.poo2.integrador.moduloSearch;

import java.time.LocalDate;

import ar.edu.unq.poo2.integrador.Sistema;
import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorFecha extends Search {
	
	Sistema sistema;
	LocalDate fechaEntrada;
	LocalDate fechaSalida;
	
	public FiltroPorFecha() {
		
	}
	
	public FiltroPorFecha( LocalDate fechaEntrada, LocalDate fechaSalida ,Sistema sistema) {
		super();
		this.sistema = sistema;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		
		return sistema.estaDisponible(inmueble, fechaEntrada, fechaSalida);
	}

}
