package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorCantidadHuespedes extends FiltroOpcional {
	
	private int cantHuespedes;
	
	public FiltroPorCantidadHuespedes(int cantidadDeHuespedes){
		this.cantHuespedes=cantidadDeHuespedes;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble){
		return inmueble.tieneCapacidadDe(this.cantHuespedes);
	}
	
}
