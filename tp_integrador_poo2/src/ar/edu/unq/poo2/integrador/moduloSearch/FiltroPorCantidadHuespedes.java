package ar.edu.unq.poo2.integrador.moduloSearch;

import java.util.List;

import ar.edu.unq.poo2.integrador.Inmueble;

public class FiltroPorCantidadHuespedes implements Search {
	
	private int cantHuespedes;
	
	public FiltroPorCantidadHuespedes(int n){
		this.cantHuespedes = n;
		
	}
	
	public int getCantidadHuespedes() {
		return this.cantHuespedes;
	}
	
	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		
		return inmuebles.stream()
						.filter((i) -> i.getCapacidad() == this.getCantidadHuespedes())
						.toList();
	}

}
