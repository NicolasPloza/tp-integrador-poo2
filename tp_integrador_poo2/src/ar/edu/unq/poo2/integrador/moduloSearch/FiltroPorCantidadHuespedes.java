package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorCantidadHuespedes extends Search {
	
	private int cantHuespedes;
	
	public FiltroPorCantidadHuespedes(int n){
		this.cantHuespedes = n;
		
	}
	
	public int getCantidadHuespedes() {
		return this.cantHuespedes;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble){
		return inmueble.getCapacidad() == this.getCantidadHuespedes();
	}
	
}
